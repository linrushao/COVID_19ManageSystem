package com.linrs.webui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/7/18 20:52
 * Desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DangerLevelBean {

    private Integer Id;
    private String Date_time;
    private String provinceName;
    private String cityName;
    private String areaName;
    private Integer dangerlevel;

}
