package com.linrs.webui.vo;

import com.linrs.webui.bean.globeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/16 15:22
 * Desc
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class globeDataVo extends globeData {
    private Integer page;
    private Integer limit;
}
