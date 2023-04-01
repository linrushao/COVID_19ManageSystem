package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.ChinaHistory_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 20:47
 * Desc
 */
@RestController
@RequestMapping("/covid")
public class ChinaHistory_Controller {
    @Autowired
    private ChinaHistory_Mapper chinaHistory_Mapper;

    /**
     * 全国历史累计趋势
     */
    @RequestMapping("/getCovidCountry_l3_c1")
    public Result getCovidCountry_l3_c1() {
        List<Map<String, Object>> data = chinaHistory_Mapper.getCovidCountry_l3_c1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 三年累积
     */

    @RequestMapping("/getCovidCountry_l3_l1")
    public Result getCovidCountry_l3_l1() {
        List<Map<String, Object>> data = chinaHistory_Mapper.getCovidCountry_l3_l1();
        Map<Object, Object> dataAll1 = new HashMap();
        Map<Object, Object> dataAll2 = new HashMap();
        Map<Object, Object> dataAll3 = new HashMap();
        List arrayListData = new ArrayList<>();
        dataAll1.put("name", data.get(0).get("累积治愈"));
        dataAll1.put("value", data.get(0).get("cured"));

        dataAll2.put("name", data.get(0).get("累积确诊"));
        dataAll2.put("value", data.get(0).get("cofirm"));

        dataAll3.put("name", data.get(0).get("累积死亡"));
        dataAll3.put("value", data.get(0).get("dead"));
        arrayListData.add(dataAll1);
        arrayListData.add(dataAll2);
        arrayListData.add(dataAll3);
        Result result = Result.success(arrayListData);

        return result;
    }

    @RequestMapping("/getCovidCountry_l3_c2")
    public Result getCovidCountry_l3_c2() {
        List<Map<String, Object>> data_2021 = chinaHistory_Mapper.getCovidCountry_l3_c2();
        List<Map<String, Object>> data_2020 = chinaHistory_Mapper.getCovidCountry_l3_l1();
        Map<Object, Object> dataAll1 = new HashMap();
        Map<Object, Object> dataAll2 = new HashMap();
        Map<Object, Object> dataAll3 = new HashMap();
        List arrayListData = new ArrayList<>();


        Object zy_2021 = data_2021.get(0).get("cured");
        Object zy_2020 = data_2020.get(0).get("cured");
        Object qz_2021 = data_2021.get(0).get("cofirm");
        Object qz_2020 = data_2020.get(0).get("cofirm");
        Object sw_2021 = data_2021.get(0).get("dead");
        Object sw_2020 = data_2020.get(0).get("dead");

        dataAll1.put("name", data_2021.get(0).get("累积治愈"));
        dataAll1.put("value", Integer.parseInt(zy_2021.toString())-Integer.parseInt(zy_2020.toString()));

        dataAll2.put("name", data_2021.get(0).get("累积确诊"));
        dataAll2.put("value", Integer.parseInt(qz_2021.toString())-Integer.parseInt(qz_2020.toString()));

        dataAll3.put("name", data_2021.get(0).get("累积死亡"));
        dataAll3.put("value", Integer.parseInt(sw_2021.toString())-Integer.parseInt(sw_2020.toString()));

        arrayListData.add(dataAll1);
        arrayListData.add(dataAll2);
        arrayListData.add(dataAll3);
        Result result = Result.success(arrayListData);
        return result;
    }

    @RequestMapping("/getCovidCountry_l3_c3")
    public Result getCovidCountry_l3_c3() {
        List<Map<String, Object>> data_2021 = chinaHistory_Mapper.getCovidCountry_l3_c2();
        List<Map<String, Object>> data_2022 = chinaHistory_Mapper.getCovidCountry_l3_c3();
        Map<Object, Object> dataAll1 = new HashMap();
        Map<Object, Object> dataAll2 = new HashMap();
        Map<Object, Object> dataAll3 = new HashMap();
        List arrayListData = new ArrayList<>();


        Object zy_2021 = data_2021.get(0).get("cured");
        Object zy_2022 = data_2022.get(0).get("cured");
        Object qz_2021 = data_2021.get(0).get("cofirm");
        Object qz_2022 = data_2022.get(0).get("cofirm");
        Object sw_2021 = data_2021.get(0).get("dead");
        Object sw_2022 = data_2022.get(0).get("dead");

        dataAll1.put("name", data_2021.get(0).get("累积治愈"));
        dataAll1.put("value", Integer.parseInt(zy_2022.toString())-Integer.parseInt(zy_2021.toString()));

        dataAll2.put("name", data_2021.get(0).get("累积确诊"));
        dataAll2.put("value", Integer.parseInt(qz_2022.toString())-Integer.parseInt(qz_2021.toString()));

        dataAll3.put("name", data_2021.get(0).get("累积死亡"));
        dataAll3.put("value", Integer.parseInt(sw_2022.toString())-Integer.parseInt(sw_2021.toString()));

        arrayListData.add(dataAll1);
        arrayListData.add(dataAll2);
        arrayListData.add(dataAll3);
        Result result = Result.success(arrayListData);
        return result;
    }
}
