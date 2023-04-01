package com.linrs.webui.vo;

import com.linrs.webui.bean.province_data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/8 20:12
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class province_dataVo extends province_data {
    private Integer page;
    private Integer limit;


}
