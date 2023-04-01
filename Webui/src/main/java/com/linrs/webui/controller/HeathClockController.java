package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.HealthClock;
import com.linrs.webui.bean.Vaccine;
import com.linrs.webui.service.service.HealthClockService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.HealthClockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/9 20:04
 * Desc
 */

@RestController
public class HeathClockController {
    @Autowired
    public HealthClockService healthClockService;

    /**
     * 查询所有打卡记录，带有模糊查询条件，带有分页
     * @param healthClockVo
     * @return
     */
    @RequestMapping("/listHealthClock")
    public DataView toHealthClock(HealthClockVo healthClockVo){
        //查询所有带有模糊查询条件，带有分页
        IPage<HealthClock> page = new Page<>(healthClockVo.getPage(),healthClockVo.getLimit());
        QueryWrapper<HealthClock> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(healthClockVo.getUsername() != null,"username",healthClockVo.getUsername());
        queryWrapper.eq(healthClockVo.getPhone() != null,"phone",healthClockVo.getPhone());
        healthClockService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加或修改健康打卡记录数据
     *
     */
    @RequestMapping("/addHealthClock")
    public DataView addHealthClock(HealthClock healthClock){
        boolean b = healthClockService.saveOrUpdate(healthClock);
        DataView dataView = new DataView();
        if (b){
            dataView.setCode(Constant.SUCCEED_STATUS);
            dataView.setMsg("添加成功");
            return dataView;
        }
        dataView.setCode(100);
        dataView.setMsg("添加失败");
        return dataView;
    }

    /**
     * 删除
     *
     */
    @RequestMapping("/deleteHealthClockById")
    public DataView deleteHealthClockById(Integer id){
        healthClockService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除数据成功");
        return dataView;
    }
}
