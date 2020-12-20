package com.otakuhuang.blog.mapper;

import com.otakuhuang.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, email, avatar, type, enabled, create_time " +
            "FROM t_user WHERE username = #{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "type", property = "type"),
            @Result(column = "enabled", property = "enabled"),
            @Result(column = "create_time", property = "createTime")
    })
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO t_user(username, email, avatar, type, enabled, password) " +
            "VALUES(#{username}, #{email}, #{avatar}, #{type}, #{enabled}, #{password})")
    int addUser(User user);

    @Update("UPDATE t_user SET enabled = #{enabled} WHERE id = #{id}")
    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("id") Long id);
}
