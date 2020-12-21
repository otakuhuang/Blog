package com.otakuhuang.blog.service;

import com.otakuhuang.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog selectBlogById(long id);

    List<Blog> selectAllBlog();

    Map<String, Object> selectBlogByPage(int currentPage);

    String addBlog(Blog blog);

    void modifyBlogById(Blog blog);

    void removeBlogById(long id);
}
