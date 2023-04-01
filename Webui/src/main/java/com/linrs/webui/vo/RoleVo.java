package com.linrs.webui.vo;

import com.linrs.webui.bean.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LRS
 * @Date 2022/8/12 11:41
 * Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends Role {
    private Integer page;
    private Integer limit;
}
