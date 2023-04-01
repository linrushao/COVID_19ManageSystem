package com.linrs.webui.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linrs.webui.bean.Role;

import java.util.List;

/**
 * @Author LRS
 * @Date 2022/8/12 11:46
 * Desc
 */
public interface RoleService extends IService<Role> {
    List<Integer> queryAllPermissionByRid(Integer roleId);

    void deleteRoleByRid(Integer rid);

    void saveRoleMenu(Integer rid, Integer mid);

    List<Integer> queryUserRoleById(Integer id);
}
