package com.linrs.webui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 12:34
 * Desc
 */
@Mapper
public interface ChinaEpidemic_Mapper {
    /**
     * 查询全国疫情汇总数据
     * @return
     */
    @Select("SELECT SUM(cofirm) AS cofirm,SUM(cofirm_now) AS cofirm_now,SUM(cofirm_add) AS cofirm_add,SUM(suspected) AS suspected,SUM(cured) AS cured,SUM(dead) AS dead FROM  (SELECT province,SUM(cofirm) AS cofirm,SUM(cofirm_now) AS cofirm_now,SUM(cofirm_add) AS cofirm_add,SUM(suspected) AS suspected,SUM(cured)AS cured,SUM(dead)AS dead FROM china_all_history_data  WHERE history_time= (SELECT history_time FROM china_all_history_data  ORDER BY history_time DESC LIMIT 1)  GROUP BY province ) AS t1;")
    List<Map<String,Object>> getNationalData_l1();

    /**
     * 重点省份累积治愈人数
     * @return
     */
    @Select("SELECT province as name,SUM(cured) AS value FROM china_all_history_data WHERE history_time=(SELECT history_time FROM china_all_history_data ORDER BY history_time DESC LIMIT 1) GROUP BY province ORDER BY cured DESC LIMIT 18")
    List<Map<String,Object>> getNationalData_l1_l1();

    /**
     * 重点省份累积确诊人数
     * @return
     */
    @Select("SELECT province as name,SUM(cofirm) AS value FROM china_all_history_data WHERE history_time=(SELECT history_time FROM china_all_history_data ORDER BY history_time DESC LIMIT 1) GROUP BY province ORDER BY cofirm DESC LIMIT 18")
    List<Map<String, Object>> getNationalData_l1_c1();


    /**
     * 累积确诊人数区域分布
     * @return
     */
    @Select("SELECT province as name,SUM(cofirm) AS value FROM china_all_history_data WHERE history_time=(SELECT history_time FROM china_all_history_data ORDER BY history_time DESC LIMIT 1) GROUP BY province ORDER BY cofirm DESC LIMIT 10")
    List<Map<String, Object>> getNationalData_l1_r1();

}
