package com.lrs.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/6/28 10:54
 * Desc 用来封装防疫物资的javaBean
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaBean {
    private String name;//物资名称
    private String from;//物资来源
    private Integer count;//物资数量
}
