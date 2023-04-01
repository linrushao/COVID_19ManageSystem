package com.linrs.webui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 20:50
 * Desc
 */
@Mapper
public interface ChinaHistory_Mapper {

    /**
     * 全国历史累计趋势
     * @return
     */
    @Select("SELECT history_time,SUM(cofirm) AS 累计确诊,SUM(cured) AS 累计治愈,SUM(dead) AS 累计死亡 FROM china_all_history_data GROUP BY history_time")
    List<Map<String, Object>> getCovidCountry_l3_c1();

    /**
     * 2020年的累积
     * @return
     */
    @Select("SELECT '累积治愈','累积确诊','累积死亡',SUM(cured) as cured,SUM(cofirm) as cofirm,SUM(dead) as dead FROM (SELECT province,SUM(cured) AS cured ,SUM(cofirm) AS cofirm,SUM(dead) AS dead FROM china_all_history_data WHERE history_time=(SELECT history_time  FROM china_all_history_data WHERE YEAR(history_time)='2020年' ORDER BY history_time DESC LIMIT 1) GROUP BY province )t1")
    List<Map<String, Object>> getCovidCountry_l3_l1();

    /**
     * 2021年的累积
     * @return
     */
    @Select("SELECT '累积治愈','累积确诊','累积死亡',SUM(cured) as cured,SUM(cofirm) as cofirm,SUM(dead) as dead FROM (SELECT province,SUM(cured) AS cured ,SUM(cofirm) AS cofirm,SUM(dead) AS dead FROM china_all_history_data WHERE history_time=(SELECT history_time  FROM china_all_history_data WHERE YEAR(history_time)='2021年' ORDER BY history_time DESC LIMIT 1) GROUP BY province )t1")
    List<Map<String, Object>> getCovidCountry_l3_c2();

    /**
     * 2022年的累积
     * @return
     */
    @Select("SELECT '累积治愈','累积确诊','累积死亡',SUM(cured) as cured,SUM(cofirm) as cofirm,SUM(dead) as dead FROM (SELECT province,SUM(cured) AS cured ,SUM(cofirm) AS cofirm,SUM(dead) AS dead FROM china_all_history_data WHERE history_time=(SELECT history_time  FROM china_all_history_data WHERE YEAR(history_time)='2022年' ORDER BY history_time DESC LIMIT 1) GROUP BY province )t1")
    List<Map<String, Object>> getCovidCountry_l3_c3();


}
