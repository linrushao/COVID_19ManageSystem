package com.linrs.webui.vo;

import com.linrs.webui.bean.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/13 20:37
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VaccineVo extends Vaccine {
    private Integer page;
    private Integer limit;

}
