package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.DormitoryData;
import com.linrs.webui.service.service.DormitoryManagerService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.DormitoryManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/16 22:11
 * Desc
 */

@RestController
@RequestMapping("/dormitory")
public class DormitoryManagerController {

    @Autowired
    private DormitoryManagerService dormitoryManagerService;

    //加载数据
    @RequestMapping("/loadAllDormitory")
    public DataView loadAllDormitory(DormitoryManagerVo dormitoryManagerVo){
        IPage<DormitoryData> page = new Page<>(dormitoryManagerVo.getPage(),dormitoryManagerVo.getLimit());
        QueryWrapper<DormitoryData> queryWrapper = new QueryWrapper<>();
        dormitoryManagerService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param dormitoryData
     * @return
     */
    @RequestMapping("/addDormitory")
    public DataView addAcademicBuilding(DormitoryData dormitoryData){
        dormitoryManagerService.save(dormitoryData);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加宿舍楼成功！！！");
        return dataView;
    }

    /**
     * 修改
     * @param dormitoryData
     * @return
     */
    @RequestMapping("/updateDormitory")
    public DataView updateDormitory(DormitoryData dormitoryData){
        dormitoryManagerService.updateById(dormitoryData);
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
    @RequestMapping("/deleteDormitory")
    public DataView deleteDormitory(Integer id){
        dormitoryManagerService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除成功！！！");
        return dataView;
    }
}
