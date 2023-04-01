package com.linrs.webui.vo;

import com.linrs.webui.bean.schoolData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/16 21:04
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SchoolDataVo extends schoolData {
    private Integer page;
    private Integer limit;

}
