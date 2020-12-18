package com.otakuhuang.blog.service;

import com.otakuhuang.blog.entity.Blog;

import java.util.List;

public interface BlogService {

    Blog selectBlogById(long id);

    List<Blog> selectAllBlog();

    void addBlog(Blog blog);

    void modifyBlogById(Blog blog);

    void removeBlogById(long id);
}
