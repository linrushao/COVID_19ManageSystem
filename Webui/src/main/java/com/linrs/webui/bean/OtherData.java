package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/17 12:50
 * Desc
 */
@Data
@TableName("other_data")
public class OtherData {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String other;
    private String name;
    private String phone;
    private String disinfect;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disinfectTime;
}

