package com.linrs.webui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrs.webui.bean.DormitoryData;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author LRS
 * @Date 2022/8/17 11:55
 * Desc
 */
@Mapper
public interface DormitoryManagerMapper extends BaseMapper<DormitoryData> {
}
