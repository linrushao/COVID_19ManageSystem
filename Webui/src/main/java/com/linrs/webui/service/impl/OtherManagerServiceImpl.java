package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.OtherData;
import com.linrs.webui.mapper.OtherManagerMapper;
import com.linrs.webui.service.service.OtherManagerService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/17 12:54
 * Desc
 */
@Service
public class OtherManagerServiceImpl extends ServiceImpl<OtherManagerMapper, OtherData> implements OtherManagerService {
}
