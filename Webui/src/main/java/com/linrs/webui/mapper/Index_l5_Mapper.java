package com.linrs.webui.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * @Author LRS
 * @Date 2022/7/17 22:30
 * Desc
 */
@Mapper
public interface Index_l5_Mapper {
    @Select("SELECT cityName,provinceName,areaName FROM city_dangerlevel WHERE dangerLevel")
    List<Map<String,String>> getCovidDangerlevel();
}
