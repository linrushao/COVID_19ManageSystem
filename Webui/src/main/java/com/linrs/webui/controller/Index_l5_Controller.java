package com.linrs.webui.controller;

import com.linrs.webui.mapper.Index_l5_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @Author LRS
 * @Date 2022/7/17 22:20
 * Desc
 */
@Controller
public class Index_l5_Controller {
    @Autowired
    private Index_l5_Mapper index_l5_mapper;

    @RequestMapping("/index_l5")
    public String getCovidDangerlevel(Model model) {
        List<Map<String,String>> datas = index_l5_mapper.getCovidDangerlevel();

        model.addAttribute("datas",datas);
        return "/index_l5.html";
    }

}
