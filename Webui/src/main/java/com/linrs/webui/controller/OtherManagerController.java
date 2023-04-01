package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.OtherData;
import com.linrs.webui.service.service.OtherManagerService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.OtherManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/16 22:11
 * Desc
 */

@RestController
@RequestMapping("/other")
public class OtherManagerController {

    @Autowired
    private OtherManagerService otherManagerService;

    //加载数据
    @RequestMapping("/loadAllOther")
    public DataView loadAllOther(OtherManagerVo otherManagerVo){
        IPage<OtherData> page = new Page<>(otherManagerVo.getPage(),otherManagerVo.getLimit());
        QueryWrapper<OtherData> queryWrapper = new QueryWrapper<>();
        otherManagerService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param otherData
     * @return
     */
    @RequestMapping("/addOther")
    public DataView addOther(OtherData otherData){
        otherManagerService.save(otherData);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加成功！！！");
        return dataView;
    }

    /**
     * 修改
     * @param otherData
     * @return
     */
    @RequestMapping("/updateOther")
    public DataView updateOther(OtherData otherData){
        otherManagerService.updateById(otherData);
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
    @RequestMapping("/deleteOther")
    public DataView deleteOther(Integer id){
        otherManagerService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除成功！！！");
        return dataView;
    }
}
