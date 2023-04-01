package com.linrs.webui.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linrs.webui.bean.UserRegisterData;

/**
 * @Author LRS
 * @Date 2022/8/18 13:29
 * Desc
 */

public interface UserRegisterService extends IService<UserRegisterData> {
    void updateBynumber(String number);
}
