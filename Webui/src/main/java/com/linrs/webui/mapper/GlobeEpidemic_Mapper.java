package com.linrs.webui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/16 14:45
 * Desc
 */
@Mapper
public interface GlobeEpidemic_Mapper {

    /**
     * 全球各洲累积确诊排行
     * @return
     */
    @Select("SELECT continents AS `name`,SUM(cofirm) AS `value` FROM (SELECT continents,cofirm FROM world_all_data WHERE history_time=(SELECT history_time FROM world_all_data ORDER BY history_time DESC LIMIT 1 ) GROUP BY country ) t1 GROUP BY continents ORDER BY cofirm DESC")
    List<Map<String, Object>> getCovidGlobal_l2_c1();

    /**
     * 全球各国确诊人数最新排名
     * @return
     */
    @Select("SELECT country AS `name`,SUM(cofirm) AS `value` FROM world_all_data WHERE history_time=(SELECT history_time FROM world_all_data ORDER BY history_time DESC LIMIT 1) GROUP BY country ORDER BY cofirm DESC LIMIT 30")
    List<Map<String, Object>> getCovidGlobal_l2_l1();

    /**
     * 全球历史累计治愈趋势
     * @return
     */
    @Select("SELECT country,history_time as `name`,cured as `value` FROM world_all_data ")
    List<Map<String, Object>> getCovidGlobal_l2_r1();
}
