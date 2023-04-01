package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.Vaccine;
import com.linrs.webui.service.service.VaccineService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.VaccineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/13 20:40
 * Desc
 */
@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;

    //加载数据
    @RequestMapping("/loadAllVaccine")
    public DataView loadAllVaccine(VaccineVo vaccineVo){
        IPage<Vaccine> page = new Page<>(vaccineVo.getPage(),vaccineVo.getLimit());
        QueryWrapper<Vaccine> queryWrapper = new QueryWrapper<>();
        vaccineService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param vaccine
     * @return
     */
    @RequestMapping("/addVaccine")
    public DataView addVaccine(Vaccine vaccine){
        vaccineService.save(vaccine);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg(Constant.ADD_SUCCEED);
        return dataView;
    }

    /**
     * 修改
     * @param vaccine
     * @return
     */
    @RequestMapping("/updateVaccine")
    public DataView updateVaccine(Vaccine vaccine){
        vaccineService.updateById(vaccine);
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
    @RequestMapping("/deleteVaccine")
    public DataView deleteVaccine(Integer id){
        vaccineService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg(Constant.DELETE_SUCCEED);
        return dataView;
    }
}
