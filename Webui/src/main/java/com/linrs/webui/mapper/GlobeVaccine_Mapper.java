package com.linrs.webui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/17 14:56
 * Desc
 */
@Mapper
public interface GlobeVaccine_Mapper {

    /**
     * 全国疫苗接种人数
     * @return
     */
    @Select("SELECT country as `name`,vaccine_total as `value` FROM all_vaccine")
    List<Map<String, Object>> getCovidVaccines_l4_c1();

    /**
     * 疫苗类型
     */
    @Select("SELECT vaccine_type_c FROM all_vaccine")
    List<Map<String,Object>> getCovidVaccines_l4_r1();

    /**
     * 柱形图全球前20名的国家接种疫苗人数
     */
    @Select("SELECT country AS `name`,vaccine_total AS `value` FROM all_vaccine ORDER BY vaccine_total DESC LIMIT 20")
    List<Map<String,Object>> getCovidVaccines_l4_l1();


    /**
     * 每百人接种人数
     */
    @Select("SELECT country as `name`,vaccine_every  as `value` FROM all_vaccine ORDER BY vaccine_total DESC LIMIT 10")
    List<Map<String,Object>> getCovidVaccines_l4_c2();

}
