package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.HeSuan;
import com.linrs.webui.service.service.HeSuanService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.HeSuanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/13 16:26
 * Desc
 */
@RestController
@RequestMapping("/hesuan")
public class HeSuanController {

    @Autowired
    private HeSuanService heSuanService;

    //加载数据
    @RequestMapping("/loadAllHeSuan")
    public DataView loadAllHeSuan(HeSuanVo heSuanVo){
        IPage<HeSuan> page = new Page<>(heSuanVo.getPage(),heSuanVo.getLimit());
        QueryWrapper<HeSuan> queryWrapper = new QueryWrapper<>();
        heSuanService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param heSuan
     * @return
     */
    @RequestMapping("/addHeSuan")
    public DataView addHeSuan(HeSuan heSuan){
        heSuanService.save(heSuan);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加核酸检测成功！！！");
        return dataView;
    }

    /**
     * 修改
     * @param heSuan
     * @return
     */
    @RequestMapping("/updateHeSuan")
    public DataView updateHeSuan(HeSuan heSuan){
        heSuanService.updateById(heSuan);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("修核酸检测成功！！");
        return dataView;
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteHeSuan")
    public DataView deleteHeSuan(Integer id){
        heSuanService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除核酸检测成功！！！");
        return dataView;
    }
}
