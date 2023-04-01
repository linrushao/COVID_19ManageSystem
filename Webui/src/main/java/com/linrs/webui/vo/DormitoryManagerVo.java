package com.linrs.webui.vo;

import com.linrs.webui.bean.AcademicBuildingData;
import com.linrs.webui.bean.DormitoryData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/17 11:58
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DormitoryManagerVo extends DormitoryData {

    private Integer page;
    private Integer limit;
}
