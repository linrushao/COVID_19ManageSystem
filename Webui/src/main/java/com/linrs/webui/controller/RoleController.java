package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.Menu;
import com.linrs.webui.bean.Role;
import com.linrs.webui.Utils.TreeNode;
import com.linrs.webui.service.service.MenuService;
import com.linrs.webui.service.service.RoleService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LRS
 * @Date 2022/8/11 20:51
 * Desc
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有角色带有分页，带有查询条件
     */
    @RequestMapping("/role/loadAllRole")
    public DataView loadAllRole(RoleVo roleVo) {
        IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
        roleService.page(page, queryWrapper);
        return new DataView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/role/addRole")
    public DataView addRole(Role role) {
        roleService.save(role);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加角色成功！！");
        return dataView;

    }


    /**
     * 删除角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/role/deleteRole")
    public DataView deleteRole(Role role) {
        roleService.removeById(role.getId());
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除角色成功！！");
        return dataView;

    }

    /**
     * 编辑角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/role/updateRole")
    public DataView updateRole(Role role) {
        roleService.saveOrUpdate(role);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("修改角色成功！！");
        return dataView;

    }

    /**
     * 初始化下拉列表的具有权限
     * 根据角色查询菜单
     * select mid from role_menu where rid = ?
     */
    @RequestMapping("/role/initPermissionByRoleId")
    public DataView initPermissionByRoleId(Integer roleId) {
        //1.把所有的菜单权限查出来
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> allPermissions = menuService.list();
        //2.根据角色rid【roleId】查询所有的菜单mid
        List<Integer> currentRolePermissions = roleService.queryAllPermissionByRid(roleId);
        //3.rid mid 所有的ID，取查询菜单和角色的数据
        List<Menu> menus = null;

        //4.查询到mid rid
        if (currentRolePermissions.size() > 0) {
            queryWrapper.in("id", currentRolePermissions);
            menus = menuService.list(queryWrapper);
        } else {
            menus = new ArrayList<>();
        }
        //5.返回前台的格式，带有层级关系 TERRNODE
        ArrayList<TreeNode> nodes = new ArrayList<>();
        for (Menu allPermission : allPermissions) {
            String checkArr = "0";
            for (Menu currentPermission : menus) {
                if (allPermission.getId().equals(currentPermission.getId())) {
                    checkArr = "1";
                    break;
                }
            }
            Boolean spread = (allPermission.getOpen() == null || allPermission.getOpen() == 1) ? true : false;
            nodes.add(new TreeNode(allPermission.getId(), allPermission.getPid(), allPermission.getTitle(), spread, checkArr));
        }

        return new DataView(nodes);

    }


    /**
     * 点击确认分配权限
     * 将数据插入数据库[role_menu]
     * var params = "rid="+data.id;
     * console.log(params);
     * $.each(permissionData,function (index, item) {
     * params+="&mids="+item.nodeId;
     * });
     */
    @RequestMapping("/role/saveRolePermission")
    public DataView saveRolePermission(Integer rid, Integer[] mids) {
        //1.根据rid删除之前的mid权限
        roleService.deleteRoleByRid(rid);
        //2.保存权限
        if (mids != null && mids.length > 0) {
            for (Integer mid : mids) {
                roleService.saveRoleMenu(rid, mid);
            }
        }
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("菜单权限分配成功");
        return dataView;

    }

}
