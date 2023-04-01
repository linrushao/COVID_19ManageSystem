package com.lrs.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用来封装各省市疫情数据的javabean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidBean {
    private String provinceName;//省份
    private String provinceShortName;//省份短名
    private String cityName;//城市名称
    private Integer currentConfirmedCount;//当前确诊人数
    private Integer confirmedCount;//累积确诊人数
    private Integer suspectedCount;//疑似病例人数
    private Integer curedCount;//治愈人数
    private Integer deadCount;//死亡人数
    private Integer locationId;//位置ID
    private Integer pid;//位置ID
    private String statisticsData;//每一天的统计数据
    private String cities;//下属城市
    private String datetime;//时间

}