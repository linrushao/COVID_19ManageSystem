package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/16 20:57
 * Desc
 */
@Data
@TableName("school_data")
public class schoolData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String age;
    private String xueYuan;
    private String zhuanYueBanJi;
    private Integer phone;
    private String card;
    private String medical;
    private String confirm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date relieveTime;
    private String remarks;



}
