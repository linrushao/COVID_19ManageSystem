package com.linrs.webui.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linrs.webui.bean.User;

/**
 * @Author LRS
 * @Date 2022/8/10 16:33
 * Desc
 */
public interface UserService extends IService<User> {
    User login(String username, String password);

    void saveUserRole(Integer uid, Integer[] ids);
}
