package com.linrs.webui.controller;

import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.UserRegisterData;
import com.linrs.webui.service.service.UserRegisterService;
import com.linrs.webui.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LRS
 * @Date 2022/8/18 13:25
 * Desc
 */
@Controller
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;
    /**
     * 添加
     * @param userRegisterData
     * @return
     */
    @RequestMapping("/adduserRegister")
    public String adduserRegister(UserRegisterData userRegisterData){
        userRegisterService.save(userRegisterData);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("注册成功！！！");
        return "redirect:/";
    }

    /**
     * 重置密码
     * @param userRegisterData
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(UserRegisterData userRegisterData) {
        String number = userRegisterData.getNumber();
        userRegisterService.updateBynumber(number);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("重置密码成功！！");

        return "redirect:/";

    }

}
