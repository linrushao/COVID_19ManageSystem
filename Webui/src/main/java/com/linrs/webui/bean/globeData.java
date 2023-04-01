package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author LRS
 * @Date 2022/8/16 15:11
 * Desc
 */
@Data
@TableName("world_all_data")
public class globeData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String country;
    private Integer cofirmAdd;
    private Integer cofirm;
    private Integer cofirmNow;
    private Integer dead;
    private Integer deadAdd;
    private Integer cured;
    private Integer curedAdd;
    private Integer suspected;
    private Integer suspectedAdd;
    private String historyTime;
}
