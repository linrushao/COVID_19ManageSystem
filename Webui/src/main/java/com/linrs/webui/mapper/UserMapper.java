package com.linrs.webui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrs.webui.bean.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author LRS
 * @Date 2022/8/10 16:33
 * Desc
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(String username, String password);
}
