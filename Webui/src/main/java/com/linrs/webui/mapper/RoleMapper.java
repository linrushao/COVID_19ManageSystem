package com.linrs.webui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrs.webui.bean.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author LRS
 * @Date 2022/8/12 11:45
 * Desc
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select mid from role_menu where rid = #{roleId}")
    List<Integer> queryMidByRid(Integer roleId);

    //1.分配菜单栏之前删除所有的rid数据
    @Delete("delete from role_menu where rid = #{rid}")
    void deleteRoleByRid(Integer rid);

    //2.保存分配角色与菜单的关系
    @Insert("insert into role_menu(rid,mid) values (#{rid},#{mid})")
    void saveRoleMenu(Integer rid, Integer mid);

    //根据用户的ID查询所有的角色
    @Select("select rid from user_role where uid = #{id}")
    List<Integer> queryUserRoleById(Integer id);


    //1.1先删除之前的用户与角色的关系
    @Delete("delete from user_role where uid=#{uid}")
    void deleteRoleUserById(Integer uid);

    @Insert("insert into user_role(uid,rid) values(#{uid},#{rid})")
    //1.2保存分配的 用户与角色之间的关系
    void saveUserRole(Integer uid, Integer rid);
}
