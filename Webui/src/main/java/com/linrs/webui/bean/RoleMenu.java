package com.linrs.webui.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author LRS
 * @Date 2022/8/11 11:54
 * Desc
 */
@Data
@TableName("role_menu")
public class RoleMenu {
    private Integer pid;//角色Id
    private Integer mid;//菜单ID
}
