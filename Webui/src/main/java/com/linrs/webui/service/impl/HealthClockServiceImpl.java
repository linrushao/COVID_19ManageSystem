package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.HealthClock;
import com.linrs.webui.mapper.HealthClockMapper;
import com.linrs.webui.service.service.HealthClockService;
import org.springframework.stereotype.Service;


/**
 * @Author LRS
 * @Date 2022/8/9 20:19
 * Desc
 */
@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockMapper, HealthClock> implements HealthClockService {
}
