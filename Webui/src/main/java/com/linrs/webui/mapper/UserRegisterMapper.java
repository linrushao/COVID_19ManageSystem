package com.linrs.webui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrs.webui.bean.UserRegisterData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Author LRS
 * @Date 2022/8/18 13:28
 * Desc
 */
@Mapper
public interface UserRegisterMapper extends BaseMapper<UserRegisterData> {
    @Update("update user_register set password = '123456' WHERE number = #{number}")
    void updateBynumber(String number);
}
