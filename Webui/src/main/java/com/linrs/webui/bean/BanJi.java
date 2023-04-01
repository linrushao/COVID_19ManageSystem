package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author LRS
 * @Date 2022/8/11 11:57
 * Desc
 */
@Data
@TableName("ban_ji")
public class BanJi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer xueYuanId;
}
