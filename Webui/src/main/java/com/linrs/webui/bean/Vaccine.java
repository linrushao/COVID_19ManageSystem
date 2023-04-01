package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/13 20:32
 * Desc
 */
@TableName("vaccine")
@Data
public class Vaccine {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String card;

    private String zhenci;
    private String pici;
    private String changjia;
    private String danwei;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//核酸检测时间

}
