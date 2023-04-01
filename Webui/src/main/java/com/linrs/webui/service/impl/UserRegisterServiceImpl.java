package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.UserRegisterData;
import com.linrs.webui.mapper.UserRegisterMapper;
import com.linrs.webui.service.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/18 13:30
 * Desc
 */
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserRegisterMapper, UserRegisterData> implements UserRegisterService {

    @Autowired
    private UserRegisterMapper userRegisterMapper;


    @Override
    public void updateBynumber(String number) {
        userRegisterMapper.updateBynumber(number);
    }
}
