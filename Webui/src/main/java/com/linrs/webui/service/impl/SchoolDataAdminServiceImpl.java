package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.schoolData;
import com.linrs.webui.mapper.SchoolDataAdminMapper;
import com.linrs.webui.service.service.SchoolDataAdminService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/16 21:03
 * Desc
 */
@Service
public class SchoolDataAdminServiceImpl extends ServiceImpl<SchoolDataAdminMapper, schoolData> implements SchoolDataAdminService {
}
