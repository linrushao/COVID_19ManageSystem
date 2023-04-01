package com.linrs.webui.controller;

import com.linrs.webui.bean.Result;
import com.linrs.webui.mapper.GlobeEpidemic_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 14:47
 * Desc
 */

@RestController
@RequestMapping("/covid")
public class GlobeEpidemic_Controller {

    @Autowired
    private GlobeEpidemic_Mapper globeEpidemic_Mapper;
    List ArrayList1 = new ArrayList();
    List ArrayList2 = new ArrayList();
    List ArrayList3 = new ArrayList();
    List ArrayList4 = new ArrayList();
    List ArrayList5 = new ArrayList();
    List ArrayList6 = new ArrayList();
    List ArrayList7 = new ArrayList();
    List ArrayList8 = new ArrayList();
    List ArrayListall = new ArrayList();

    /**
     * 全球各洲累积确诊排行
     */
    @RequestMapping("/getCovidGlobal_l2_c1")
    public Result getCovidGlobal_l2_c1() {
        List<Map<String, Object>> data = globeEpidemic_Mapper.getCovidGlobal_l2_c1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 全球各国确诊人数最新排名
     */
    @RequestMapping("/getCovidGlobal_l2_l1")
    public Result getCovidGlobal_l2_l1() {
        List<Map<String, Object>> data = globeEpidemic_Mapper.getCovidGlobal_l2_l1();
        Result result = Result.success(data);
        return result;
    }

    /**
     * 全球历史累计治愈趋势
     */
    @RequestMapping("/getCovidGlobal_l2_r1")
    public Result getCovidGlobal_l2_r1() {
        List<Map<String, Object>> data = globeEpidemic_Mapper.getCovidGlobal_l2_r1();

        for (Map datas : data) {
            if (datas.get("country").equals("中国")) {
                ArrayList1.add(datas);
            }
            if (datas.get("country").equals("美国")) {
                ArrayList2.add(datas);
            }
            if (datas.get("country").equals("俄罗斯")) {
                ArrayList3.add(datas);
            }
            if (datas.get("country").equals("日本")) {
                ArrayList4.add(datas);
            }
            if (datas.get("country").equals("印度")) {
                ArrayList5.add(datas);
            }
            if (datas.get("country").equals("巴西")) {
                ArrayList6.add(datas);
            }
            if (datas.get("country").equals("法国")) {
                ArrayList7.add(datas);
            }
            if (datas.get("country").equals("韩国")) {
                ArrayList8.add(datas);
            }
        }
        ArrayListall.add(ArrayList1);
        ArrayListall.add(ArrayList2);
        ArrayListall.add(ArrayList3);
        ArrayListall.add(ArrayList4);
        ArrayListall.add(ArrayList5);
        ArrayListall.add(ArrayList6);
        ArrayListall.add(ArrayList7);
        ArrayListall.add(ArrayList8);

        Result result = Result.success(ArrayListall);
        return result;
    }

}
