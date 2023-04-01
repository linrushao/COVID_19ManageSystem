package com.linrs.webui.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.User;
import com.linrs.webui.service.service.UserService;
import com.linrs.webui.vo.DataView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author LRS
 * @Date 2022/8/10 15:19
 * Desc
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 验证码的逻辑
     *
     * @param response
     * @param session
     * @throws IOException
     */

    @RequestMapping("/login/getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //1.验证码对象。hutool定义图形验证码的长和宽，验证码的位数，干扰线的条数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 10);
        //2.放入到session
        session.setAttribute("code", lineCaptcha.getCode());
        //3.输出验证码
        ServletOutputStream outputstream = response.getOutputStream();
        lineCaptcha.write(outputstream);
        //4.关闭输出流
        outputstream.close();
    }

    /**
     * 具体的登录逻辑
     */
    @RequestMapping("/login/login")
    public DataView login(String username, String password, String code, HttpSession session) {
        DataView dataView = new DataView();

        //1.首先判断验证码对不对
        String sessionCode = (String) session.getAttribute("code");
        if (code != null && sessionCode.equals(code)){
            //2.session普通登录  登录逻辑
            User user = userService.login(username, password);
            //shiro登录
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            subject.login(token);
//            User user= (User) subject.getPrincipal();

            //3.判断

            if (user != null) {
                dataView.setCode(Constant.SUCCEED_STATUS);
                dataView.setMsg("登录成功");
                //放入session
                session.setAttribute("user",user);
                return dataView;
            } else {
                dataView.setCode(100);
                dataView.setMsg("用户名或密码错误，登录失败");

                return dataView;
            }
        }else {
            dataView.setCode(100);
            dataView.setMsg("验证码错误");
            return dataView;
        }
    }

}
