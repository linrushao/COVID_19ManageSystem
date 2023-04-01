package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.LibraryManager;
import com.linrs.webui.service.service.LibraryManagerService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.LibraryManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/16 22:11
 * Desc
 */

@RestController
@RequestMapping("/library")
public class libraryManagerController {

    @Autowired
    private LibraryManagerService libraryManagerService;

    //加载数据
    @RequestMapping("/loadAllLibrary")
    public DataView loadAllLibrary(LibraryManagerVo libraryManagerVo){
        IPage<LibraryManager> page = new Page<>(libraryManagerVo.getPage(),libraryManagerVo.getLimit());
        QueryWrapper<LibraryManager> queryWrapper = new QueryWrapper<>();
        libraryManagerService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param libraryManager
     * @return
     */
    @RequestMapping("/addLibrary")
    public DataView addLibrary(LibraryManager libraryManager){
        libraryManagerService.save(libraryManager);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加区域成功！！！");
        return dataView;
    }

    /**
     * 修改
     * @param libraryManager
     * @return
     */
    @RequestMapping("/updateLibrary")
    public DataView updateLibrary(LibraryManager libraryManager){
        libraryManagerService.updateById(libraryManager);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("修改成功！！");
        return dataView;
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteLibrary")
    public DataView deleteLibrary(Integer id){
        libraryManagerService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除成功！！！");
        return dataView;
    }
}
