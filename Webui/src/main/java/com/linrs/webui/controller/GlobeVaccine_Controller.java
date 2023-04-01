package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.GlobeVaccine_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author LRS
 * @Date 2022/7/17 14:55
 * Desc
 */
@RestController
@RequestMapping("/covid")
public class GlobeVaccine_Controller {
    @Autowired
    private GlobeVaccine_Mapper globeVaccine_mapper;

    /**
     * 全球疫苗接种人数
     */
    @RequestMapping("/getCovidVaccines_l4_c1")
    public Result getCovidVaccines_l4_c1() {
        List<Map<String, Object>> data = globeVaccine_mapper.getCovidVaccines_l4_c1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 全球疫苗类型
     */
    @RequestMapping("/getCovidVaccines_l4_r1")
    public Result getCovidVaccines_l4_r1() {
        List<Map<String, Object>> data = globeVaccine_mapper.getCovidVaccines_l4_r1();
        List<String> vaccine_data = new ArrayList();
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int i=0;i<data.size();i++) {
            String[] vaccine_type_cs = String.valueOf(data.get(i).get("vaccine_type_c")).split(",");
            for (String a:vaccine_type_cs){
                vaccine_data.add(a);
            }
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet(vaccine_data);
        ArrayList<String> set = new ArrayList(hashSet);
        List data_all = new ArrayList();
        for (String str:set ){
            for (String lstr:vaccine_data) {
                if (str.equals(lstr)) {
                    if (map.containsKey(str)) {//判断如果key中已存在该字符串
                        Integer count = map.get(str);
                        count++;
                        map.put(str, count);//value值
                    } else {
                        map.put(str, 1);//如果该字符串没有出现 map新保存一组数据  出现次数为1次
                    }
                }
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            Map map2 = zhuanhuan(key, value);
            data_all.add(map2);
        }

        Result result = Result.success(data_all);
        return result;
    }

    public Map zhuanhuan(String key,Integer value){
        Map map1 = new HashMap();
        map1.put("name",key);
        map1.put("value",value);
        return map1;
    }

    /**
     * 柱形图全球前20名的国家接种疫苗人数
     */
    @RequestMapping("/getCovidVaccines_l4_l1")
    public Result getCovidVaccines_l4_l1(){
        List<Map<String, Object>> data = globeVaccine_mapper.getCovidVaccines_l4_l1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 每百人接种人数
     */
    @RequestMapping("/getCovidVaccines_l4_c2")
    public Result getCovidVaccines_l4_c2(){
        List<Map<String, Object>> data = globeVaccine_mapper.getCovidVaccines_l4_c2();
        Result result = Result.success(data);
        return result;
    }


}
