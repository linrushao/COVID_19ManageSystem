package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.Vaccine;
import com.linrs.webui.mapper.VaccineMapper;
import com.linrs.webui.service.service.VaccineService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/13 20:38
 * Desc
 */
@Service
public class VaccineServiceImpl extends ServiceImpl<VaccineMapper, Vaccine> implements VaccineService {
}
