package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.AcademicBuildingData;
import com.linrs.webui.service.service.AcademicBuildingDataService;
import com.linrs.webui.vo.AcademicBuildingDataVo;
import com.linrs.webui.vo.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/16 22:11
 * Desc
 */

@RestController
@RequestMapping("/academic")
public class AcademicBuildingDataController {

    @Autowired
    private AcademicBuildingDataService academicBuildingDataService;

    //加载数据
    @RequestMapping("/loadAllAcademicBuilding")
    public DataView loadAllAcademicBuilding(AcademicBuildingDataVo academicBuildingDataVo){
        IPage<AcademicBuildingData> page = new Page<>(academicBuildingDataVo.getPage(),academicBuildingDataVo.getLimit());
        QueryWrapper<AcademicBuildingData> queryWrapper = new QueryWrapper<>();
        academicBuildingDataService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param academicBuildingData
     * @return
     */
    @RequestMapping("/addAcademicBuilding")
    public DataView addAcademicBuilding(AcademicBuildingData academicBuildingData){
        academicBuildingDataService.save(academicBuildingData);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg(Constant.ADD_SUCCEED);
        return dataView;
    }

    /**
     * 修改
     * @param academicBuildingData
     * @return
     */
    @RequestMapping("/updateAcademicBuilding")
    public DataView updateAcademicBuilding(AcademicBuildingData academicBuildingData){
        academicBuildingDataService.updateById(academicBuildingData);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg(Constant.UPDATE_SUCCEED);
        return dataView;
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteAcademicBuilding")
    public DataView deleteAcademicBuilding(Integer id){
        academicBuildingDataService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg(Constant.DELETE_SUCCEED);
        return dataView;
    }
}
