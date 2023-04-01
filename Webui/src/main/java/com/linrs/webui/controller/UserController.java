package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.BanJi;
import com.linrs.webui.bean.User;
import com.linrs.webui.bean.XueYuan;
import com.linrs.webui.service.service.BanJiService;
import com.linrs.webui.service.service.RoleService;
import com.linrs.webui.service.service.UserService;
import com.linrs.webui.service.service.XueYuanService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author LRS
 * @Date 2022/8/12 22:20
 * Desc
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BanJiService banJiService;
    @Autowired
    private XueYuanService xueYuanService;
    @Autowired
    private RoleService roleService;

    /**
     * 分页查询所有用户数据带有查询条件
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/loadAllUser")
    public DataView getAllUser(UserVo userVo) {

        //1.1第一种方法
//        if(StringUtils.isNotBlank(userVo.getUsername())){
//            userService.loaddUserByLeftJoin(userVo.getUsername(),userVo.getPage(),userVo.getLimit());
//        }
        //1.2 mapper
        //@Select("select a.username,b.name from user as a where a.username = #{} left join b.userid = a.userid limit #{},#{}"

        //2.第二种方法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getUsername()), "username", userVo.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getPhone()), "phone", userVo.getPhone());
        IPage<User> ipage = userService.page(page, queryWrapper);


        for (User user : ipage.getRecords()) {
            //为班级名字进行赋值
            if (user.getBanJiId() != null) {
                //班级BanJiService查库
                BanJi banji = banJiService.getById(user.getBanJiId());
                user.setBanJiName(banji.getName());
            }

            //为学院名字进行赋值
            if (user.getXueYuanId() != null) {
                XueYuan xueyuan = xueYuanService.getById(user.getXueYuanId());
                user.setXueYuanName(xueyuan.getName());
            }

            //为老师名字进行赋值
            if (user.getTeacherId() != null) {
                User teacher = userService.getById(user.getTeacherId());
                user.setTeacherName(teacher.getUsername());
            }
        }

        return new DataView(ipage.getTotal(), ipage.getRecords());
    }

    /**
     * 新增用户
     */

    @RequestMapping("/addUser")
    public DataView addUser(User user) {

        userService.save(user);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加用户成功");
        return dataView;
    }

    /**
     * 初始化下拉列表的数据【班级】
     */
    @RequestMapping("/listAllBanJi")
    public List<BanJi> listAllBanJi() {
        List<BanJi> list = banJiService.list();
        return list;
    }

    /**
     * 初始化下拉列表的数据【学院】
     */
    @RequestMapping("/listAllXueYuan")
    public List<XueYuan> listAllXueYuan() {
        List<XueYuan> list = xueYuanService.list();
        return list;
    }

    /**
     * 修改用户
     */
    @RequestMapping("/updateUser")
    public DataView updateUser(User user) {

        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("修改用户成功!!");
        return dataView;
    }


    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser/{id}")
    public DataView deleteUser(@PathVariable("id") Integer id) {

        userService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除用户成功!!");
        return dataView;
    }

    /**
     * 重置密码
     */
    @RequestMapping("/resetPwd/{id}")
    public DataView resetPwd(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setPassword("123456");
        userService.updateById(user);
        DataView dataView = new DataView();
        dataView.setMsg("重置密码成功！！初始密码为：123456");
        dataView.setCode(Constant.SUCCEED_STATUS);
        return dataView;

    }

    /**
     * 点击分配角色的时候，初始化用户角色
     * 打开点击分配角色的弹出层
     * 根据Id查询所拥有的角色
     */
    @RequestMapping("/initRoleByUserId")
    public DataView initRoleByUserId(Integer id) {
        //1.查询所有角色
        List<Map<String, Object>> listMaps = roleService.listMaps();

        //2.查询当前登录用户所拥有的角色
        List<Integer> currentUserRoleIds = roleService.queryUserRoleById(id);

        //3.让你的前端变为选中状态
        for (Map<String, Object> map : listMaps) {
            boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                if (rid.equals(roleId)) {
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户与角色之间的关系 1：m
     * 先删除再保存关系
     */

    @RequestMapping("/saveUserRole")
    public DataView saveUserRole(Integer uid, Integer[] ids) {
        userService.saveUserRole(uid, ids);
        DataView dataView = new DataView();
        dataView.setMsg("用户角色分配成功");
        dataView.setCode(Constant.SUCCEED_STATUS);
        return dataView;
    }

    /**
     * 图片功能
     * 图片上传测试
     */

    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\CODE\\JavaCODE\\44_COVID19\\Webui\\src\\main\\resources\\static\\images\\" + dateStr+"\\"+uuid+"." + prefix;
//                String filepath = request.getSession().getServletContext().getRealPath("images")+"\\headshot\\"+uuid+"." + prefix;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/images"+"/"+dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){

        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }

}
