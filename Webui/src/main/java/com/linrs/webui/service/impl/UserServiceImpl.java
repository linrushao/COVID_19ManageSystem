package com.linrs.webui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linrs.webui.bean.User;
import com.linrs.webui.mapper.RoleMapper;
import com.linrs.webui.mapper.UserMapper;
import com.linrs.webui.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LRS
 * @Date 2022/8/10 16:35
 * Desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public User login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        roleMapper.deleteRoleUserById(uid);
        if(ids!=null && ids.length>0){
            for (Integer rid : ids){
                roleMapper.saveUserRole(uid,rid);

            }
        }
    }
}
