package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/8 12:36
 * Desc
 */
@Data
@TableName("province_data")
public class province_data {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String province;
    private Integer cofirm;
    private Integer suspect;
    private Integer cured;
    private Integer dead;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastUpdateTime;

}
