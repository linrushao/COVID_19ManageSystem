package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.Menu;
import com.linrs.webui.Utils.TreeNode;
import com.linrs.webui.Utils.TreeNodeBuilder;
import com.linrs.webui.bean.User;
import com.linrs.webui.service.service.MenuService;
import com.linrs.webui.service.service.RoleService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @Author LRS
 * @Date 2022/8/11 16:04
 * Desc
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 加载所有菜单
     *
     * @param menuVo
     * @return
     */
    @RequestMapping("/covidManageSystem/loadAllMenu")
    public DataView loadAllMenu(MenuVo menuVo) {
        IPage<Menu> page = new Page<>(menuVo.getPage(), menuVo.getLimit());
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(menuVo.getTitle()), "title", menuVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        menuService.page(page);
        return new DataView(page.getTotal(), page.getRecords());

    }

    /**
     * 加载下拉菜单出局，初始化下拉树
     */
    @RequestMapping("/covidManageSystem/loadMenuManagerLeftTreeJson")
    public DataView loadMenuManagerLeftTreeJson() {
        List<Menu> list = menuService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu menu : list) {
            boolean open = menu.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), open));
        }

        return new DataView(treeNodes);
    }


    /**
     * 赋值最大的排序码+1
     * 条件查询：倒叙排序，取一条数据
     */

    @RequestMapping("/covidManageSystem/loadMenuMaxOrderNum")
    public Map<String, Object> loadMenuMaxOrderNum() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Menu> page = new Page<>(1, 1);
        List<Menu> list = menuService.page(page, queryWrapper).getRecords();
        map.put("value", list.get(0).getOrdernum() + 1);
        return map;
    }


    /**
     * 新增菜单
     */
    @RequestMapping("/covidManageSystem/addMenu")
    public DataView addMenu(Menu menu) {
        DataView dataView = new DataView();
        menu.setType("menu");
        boolean save = menuService.save(menu);
        if (!save) {
            dataView.setCode(100);
            dataView.setMsg("菜单插入失败！！");
        }
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("菜单插入成功！！");
        return dataView;
    }

    /**
     * 更新菜单
     */
    @RequestMapping("/covidManageSystem/updateMenu")
    public DataView updateMenu(Menu menu) {
        menuService.updateById(menu);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("更新菜单成功！");
        return dataView;
    }

    /**
     * 删除菜单
     * 判断有没有子类ID
     * 没有子类ID，可以删除
     */
    @RequestMapping("/covidManageSystem/checkMenuHasChildrenNode")
    public Map<String, Object> checkMenuHasChildrenNode(Menu menu) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", menu.getId());
        List<Menu> list = menuService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 真正删除
     */
    @RequestMapping("/covidManageSystem/deleteMenu")
    public DataView deleteMenu(Menu menu) {
        menuService.removeById(menu.getId());
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除菜单成功！！");
        return dataView;
    }

    /**
     * 加载主页面index的菜单栏，带有层级关系
     * 【不同的用户登录看到不同的菜单栏】 加查询条件
     */
    @RequestMapping("/covidManageSystem/loadIndexLeftMenuJson")
    public DataView loadIndexLeftMenuJson(Menu menu, HttpSession session) {

        //查询所有菜单栏，按照条件查询【管理员，其他学生，老师【条件查询】】
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> list = null;

        //1.取出session中的用户ID
        User user = (User) session.getAttribute("user");
        Integer userID = user.getId();

        //判断是不是admin管理员
        if (user.getUsername().equals("admin") || StringUtils.equals(user.getUsername(), "admin")) {
            list = menuService.list();
        } else {
            //2.根据用户ID查询角色ID
            List<Integer> currentUserRoleIds = roleService.queryUserRoleById(userID);

            //3.去重
            Set<Integer> mids = new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                //3.1根据角色ID查询菜单栏ID
                List<Integer> permissionIds = roleService.queryAllPermissionByRid(rid);
                //3.2菜单栏ID和角色ID去重
                mids.addAll(permissionIds);

            }
            //4.根据角色查询菜单栏ID
            if (mids.size() > 0) {
                queryWrapper.in("id", mids);
                list = menuService.list(queryWrapper);

            }
        }

        //构造层级关系
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        for (Menu m : list) {
            Integer id = m.getId();
            Integer pid = m.getPid();
            String title = m.getTitle();
            String icon = m.getIcon();
            String href = m.getHref();
            Boolean open = m.getOpen().equals(1) ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, open));
        }
        //层级关系
        List<TreeNode> nodeList = TreeNodeBuilder.build(treeNodes, 0);
        return new DataView(nodeList);
    }

}
