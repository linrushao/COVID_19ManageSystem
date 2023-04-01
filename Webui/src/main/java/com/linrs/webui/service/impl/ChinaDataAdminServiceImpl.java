package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.province_data;
import com.linrs.webui.mapper.ChinaDataAdminMapper;
import com.linrs.webui.service.service.chinaDataAdminService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/8 12:56
 * Desc
 */
@Service
public class ChinaDataAdminServiceImpl extends ServiceImpl<ChinaDataAdminMapper, province_data> implements chinaDataAdminService {
}
