package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.ChinaEpidemic_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 12:33
 * Desc
 */
@RestController
@RequestMapping("/covid")
public class ChinaEpidemic_Controller {
    @Autowired
    private ChinaEpidemic_Mapper chinaEpidemic_Mapper;

    /**
     * 接收前端请求返回全国疫情汇总数据
     */
    @RequestMapping("/getNationalData_l1")
    public Result getNationalData(){
        Map<String, Object> data = chinaEpidemic_Mapper.getNationalData_l1().get(0);
        Result result = Result.success(data);
        return result;
    }

    /**
     * 重点省份累积治愈人数
     */
    @RequestMapping("/getNationalData_l1_l1")
    public Result getNationalData_l1_l1(){
        List<Map<String, Object>> data = chinaEpidemic_Mapper.getNationalData_l1_l1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 重点省份累积确诊人数
     */
    @RequestMapping("/getNationalData_l1_c1")
    public Result getNationalData_l1_c1(){
        List<Map<String, Object>> data = chinaEpidemic_Mapper.getNationalData_l1_c1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 累积确诊人数区域分布
     */
    @RequestMapping("/getNationalData_l1_r1")
    public Result getNationalData_l1_r1(){
        List<Map<String, Object>> data = chinaEpidemic_Mapper.getNationalData_l1_r1();
        Result result = Result.success(data);
        return result;
    }
}
