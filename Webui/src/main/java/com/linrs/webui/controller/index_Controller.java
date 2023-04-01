package com.linrs.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LRS
 * @Date 2022/8/15 22:10
 * Desc
 */

@Controller
public class index_Controller {
    //echart图
    @RequestMapping("/chinaEpidemic")
    public String index_l1(){
        return "chinaEpidemic.html";
    }
    @RequestMapping("/globeEpidemic")
    public String index_l2(){
        return "globeEpidemic.html";
    }
    @RequestMapping("/chinaHistory")
    public String index_l3(){
        return "chinaHistory.html";
    }
    @RequestMapping("/globeVaccine")
    public String index_l4(){
        return "globeVaccine.html";
    }
    @RequestMapping("/jingWaiAndCovidWz")
    public String main(){
        return "jingWaiAndCovidWz.html";
    }
}
