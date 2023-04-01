package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.CovidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/15 9:58
 * Desc  用来接收前端数据请求的Controller
 */
@RestController//=@Controller+@ResponseBody  表示该类是SpringBoot的一个Controller ，且返回的数据为json格式
@RequestMapping("/covid")
public class Covid19Controller {

    @Autowired
    private CovidMapper covidMapper;

    /**
     * 接收前端请求返回全国疫情汇总数据
     */
    @RequestMapping("/getNationalData")
    public Result getNationalData(){
//        System.out.println("接收到前端发起的获取json数据的请求，后续需要查询MySQL将数据返回");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = new Date();
//        String datetime = format.format(time);
//        System.out.println(datetime);
        String datetime  = "2022-07-12";
        Map<String, Object> data = covidMapper.getNationalData(datetime).get(0);
        Result result = Result.success(data);
        return result;
    }

    //getNationalMapData
    /**
     * 查询全国各省份累积确诊数据并返回
     */
    @RequestMapping("/getNationalMapData")
    public Result getNationalMapData(){
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = new Date();
//        String datetime = format.format(time);
//        System.out.println(datetime);
        String datetime  = "2022-07-12";
        List<Map<String, Object>> data = covidMapper.getNationalMapData(datetime);
        Result result  = Result.success(data);
        return result;
    }


    //getCovidTimeData
    /**
     * 查询全国每一天的疫情数据并返回
     */
    @RequestMapping("/getCovidTimeData")
    public Result getCovidTimeData(){
        List<Map<String, Object>> data =  covidMapper.getCovidTimeData();
        return Result.success(data);
    }


    //getCovidImportData
    /**
     * 查询各省份境外输入病例数量
     */
    @RequestMapping("/getCovidImportData")
    public Result getCovidImportData(){
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = new Date();
//        String datetime = format.format(time);
//        System.out.println(datetime);
        String datetime  = "2022-07-12";
        List<Map<String, Object>> data = covidMapper.getCovidImportData(datetime);
        return  Result.success(data);
    }

    //getCovidWz
    /**
     * 查询各物资使用情况
     */
    @RequestMapping("/getCovidWz")
    public Result getCovidWz(){
        List<Map<String, Object>> data = covidMapper.getCovidWz();
        return Result.success(data);
    }

}
