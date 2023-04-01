package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/17 11:52
 * Desc
 */
@Data
@TableName("academic_building_data")
public class AcademicBuildingData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String academicBuilding;
    private String name;
    private String phone;
    private String disinfect;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disinfectTime;

}
