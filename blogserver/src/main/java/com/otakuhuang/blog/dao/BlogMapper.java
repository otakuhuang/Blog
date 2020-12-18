package com.otakuhuang.blog.dao;

import com.otakuhuang.blog.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author otaku
 */
@Mapper
public interface BlogMapper {

    @Select("SELECT id, title, sub_title, flag, first_picture, content, appreciation, published, commentabled, recommend," +
            "share_statement, views, type_id, user_id, is_save, is_delete, create_time, update_time " +
            "FROM t_blog WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "sub_title", property = "subTitle"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "first_picture", property = "firstPicture"),
            @Result(column = "content", property = "content"),
            @Result(column = "appreciation", property = "appreciation"),
            @Result(column = "published", property = "published"),
            @Result(column = "commentabled", property = "commentabled"),
            @Result(column = "recommend", property = "recommend"),
            @Result(column = "share_statement", property = "shareStatement"),
            @Result(column = "views", property = "views"),
            @Result(column = "type_id", property = "typeId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "is_save", property = "isSave"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Blog findBlogById(@Param("id") Long id);

    @Select("SELECT id, title, sub_title, flag, first_picture, content, appreciation, published, commentabled, recommend," +
            "share_statement, views, type_id, user_id, is_save, is_delete, create_time, update_time " +
            "FROM t_blog")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "sub_title", property = "subTitle"),
            @Result(column = "flag", property = "flag"),
            @Result(column = "first_picture", property = "firstPicture"),
            @Result(column = "content", property = "content"),
            @Result(column = "appreciation", property = "appreciation"),
            @Result(column = "published", property = "published"),
            @Result(column = "commentabled", property = "commentabled"),
            @Result(column = "recommend", property = "recommend"),
            @Result(column = "share_statement", property = "shareStatement"),
            @Result(column = "views", property = "views"),
            @Result(column = "type_id", property = "typeId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "is_save", property = "isSave"),
            @Result(column = "is_delete", property = "isDelete"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Blog> findAllBlog();

    @Insert("INSERT INTO t_blog(title,sub_title,flag,first_picture,content,appreciation,published,commentabled,recommend," +
            "share_statement,views,type_id,user_id,is_save,is_delete) " +
            "VALUES(#{title}, #{subTitle}, #{flag}, #{firstPicture}, #{content}, #{appreciation}, #{published}, #{commentabled}, #{recommend}, #{shareStatement}, #{views}, #{typeId}," +
            "#{userId}, #{isSave}, #{isDelete})")
    void insertBlog(Blog blog);

    @Update("UPDATE t_blog SET title = #{title}, sub_title = #{subTitle}, flag = #{flag}, first_picture = #{firstPicture}," +
            "content = #{content}, appreciation = #{appreciation}, published = #{published}, commentabled = #{commentabled}," +
            "recommend = #{recommend}, share_statement = #{shareStatement}, views = #{views}, type_id = #{typeId}, user_id = #{userId}," +
            "is_save = #{isSave}, is_delete = #{isDelete} WHERE id = #{id}")
    void updateBlogById(Blog blog);

    @Update("UPDATE t_blog SET is_delete = 1 WHERE id = #{id}")
    void deleteBlogById(@Param("id") Long id);
}
