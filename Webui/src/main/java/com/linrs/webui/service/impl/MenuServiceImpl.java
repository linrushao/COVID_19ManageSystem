package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.Menu;
import com.linrs.webui.mapper.MenuMapper;
import com.linrs.webui.service.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/11 17:03
 * Desc
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
