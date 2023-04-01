package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author LRS
 * @Date 2022/8/18 13:26
 * Desc
 */
@TableName("user_register")
@Data
public class UserRegisterData {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String number;
    private String password;
}
