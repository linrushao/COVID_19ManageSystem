package com.linrs.webui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linrs.webui.Utils.Constant;
import com.linrs.webui.bean.schoolData;
import com.linrs.webui.service.service.SchoolDataAdminService;
import com.linrs.webui.vo.DataView;
import com.linrs.webui.vo.SchoolDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LRS
 * @Date 2022/8/16 20:46
 * Desc
 */

@RestController
@RequestMapping("/school")
public class SchoolDataAdminController {
    @Autowired
    private SchoolDataAdminService schoolDataAdminService;

    //加载数据
    @RequestMapping("/loadAllSchoolData")
    public DataView loadAllSchoolData(SchoolDataVo schoolDataVo){
        IPage<schoolData> page = new Page<>(schoolDataVo.getPage(),schoolDataVo.getLimit());
        QueryWrapper<schoolData> queryWrapper = new QueryWrapper<>();
        schoolDataAdminService.page(page,queryWrapper);
        return new DataView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加
     * @param schooldata
     * @return
     */
    @RequestMapping("/addSchoolData")
    public DataView addSchoolData(schoolData schooldata){
        schoolDataAdminService.save(schooldata);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("添加成功！！！");
        return dataView;
    }

    /**
     * 修改
     * @param schooldata
     * @return
     */
    @RequestMapping("/updateSchoolData")
    public DataView updateSchoolData(schoolData schooldata){
        schoolDataAdminService.updateById(schooldata);
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
    @RequestMapping("/deleteSchoolData")
    public DataView deleteSchoolData(Integer id){
        schoolDataAdminService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(Constant.SUCCEED_STATUS);
        dataView.setMsg("删除成功！！！");
        return dataView;
    }
}
