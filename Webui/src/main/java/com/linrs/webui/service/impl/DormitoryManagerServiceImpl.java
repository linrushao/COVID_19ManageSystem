package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.DormitoryData;
import com.linrs.webui.mapper.DormitoryManagerMapper;
import com.linrs.webui.service.service.DormitoryManagerService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/17 12:30
 * Desc
 */

@Service
public class DormitoryManagerServiceImpl extends ServiceImpl<DormitoryManagerMapper, DormitoryData> implements DormitoryManagerService {
}
