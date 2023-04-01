package com.linrs.webui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrs.webui.bean.province_data;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author LRS
 * @Date 2022/8/8 12:47
 * Desc  接口：只有方法定义，写业务逻辑
 * 1.实现类，实现你自己的业务逻辑
 * 2.xml mybatisplus 一种实现
 *
 * 3.@select
 */
@Mapper
public interface ChinaDataAdminMapper extends BaseMapper<province_data> {
}
