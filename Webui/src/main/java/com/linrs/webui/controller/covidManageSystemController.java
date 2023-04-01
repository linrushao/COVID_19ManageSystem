package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.ChinaEpidemic_Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/8/7 20:51
 * Desc
 */

@Controller
@RequestMapping("/covidManageSystem")
public class covidManageSystemController {
    @Autowired
    private ChinaEpidemic_Mapper chinaEpidemic_Mapper;


    /**
     * 首页
     * @return
     */
    @RequestMapping("/home")
    public String covidmanagesystem_index(){
        return "/covidManageSystem/home.html";
    }


    /**
     * 系统的显示首页
     * @return
     */
    @RequestMapping("/welcome")
    public String covidmanagesystem_welcome(Model model){
        Map<String, Object> data = chinaEpidemic_Mapper.getNationalData_l1().get(0);
        model.addAttribute("cofirm",data.get("cofirm"));
        model.addAttribute("cofirm_now",data.get("cofirm_now"));
        model.addAttribute("cofirm_add",data.get("cofirm_add"));
        model.addAttribute("suspected",data.get("suspected"));
        model.addAttribute("cured",data.get("cured"));
        model.addAttribute("dead",data.get("dead"));

        return "/covidManageSystem/welcome.html";
    }

    /**
     * 其他区域消毒管理
     * @return
     */
    @RequestMapping("/OtherManager")
    public String covidmanagesystem_OtherManager(){
        return "/covidManageSystem/OtherManager.html";
    }

    /**
     * 宿舍楼消毒管理
     * @return
     */
    @RequestMapping("/dormitoryManager")
    public String covidmanagesystem_dormitoryManager(){
        return "/covidManageSystem/dormitoryManager.html";
    }


    /**
     * 教学楼消毒管理
     * @return
     */
    @RequestMapping("/AcademicBuildingManager")
    public String covidmanagesystem_AcademicBuildingManager(){
        return "/covidManageSystem/AcademicBuildingManager.html";
    }

    /**
     * 图书馆消毒信息管理
     * @return
     */
    @RequestMapping("/libraryManager")
    public String covidmanagesystem_libraryManager(){
        return "/covidManageSystem/libraryManager.html";
    }


    /**
     * 健康打卡记录
     * @return
     */
    @RequestMapping("/HealthClock")
    public String covidmanagesystem_HealthClock(){
        return "/covidManageSystem/HealthClock.html";
    }

    /**
     * 全球疫情数据管理查询
     * @return
     */
    @RequestMapping("/globeEpidemicManager")
    public String covidmanagesystem_globeEpidemicManager(){

        return "/covidManageSystem/globeEpidemicManager.html";
    }

    /**
     * 中国疫情数据管理查询
     * @return
     */
    @RequestMapping("/chinaEpidemicManager")
    public String covidmanagesystem_chinaEpidemicManager(){
        return "/covidManageSystem/chinaEpidemicManager.html";
    }

    /**
     * 校园疫情数据管理
     * @return
     */
    @RequestMapping("/schoolEpidemicManager")
    public String covidmanagesystem_schoolEpidemicManager(){
        return "/covidManageSystem/schoolEpidemicManager.html";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String Logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/index.html";
    }


    /**
     * 核酸检测
     */
    @RequestMapping("/toHeSuan")
    public String toHeSuan(){
        return "hesuan/hesuan.html";
    }

    /**
     * 疫苗管理
     * @return
     */
    @RequestMapping("/toVaccine")
    public String toVaccine(){
        return "vaccine/vaccine.html";
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping("/toUser")
    public String toUser(){
        return "user/user.html";
    }

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("/toUserInfo")
    public String toUserInfo(){
        return "user/userInfo.html";
    }
    @RequestMapping("/toChangePassword")
    public String toChangepassword(){
        return "user/changepassword.html";
    }

    /**
     * 菜单
     */
    @RequestMapping("/toMenu")
    public String toMenu(){
        return "menu/menu.html";
    }

    /**
     * 角色管理
     */
    @RequestMapping("/toRole")
    public String toRole(){
        return "role/role.html";
    }
}
