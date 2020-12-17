package com.jamhuang.blog.service;

import com.jamhuang.blog.entity.Blog;
import com.jamhuang.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Page<Blog> listSearchBlog(String searchContent, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    Long countBlogByTypeId(Long typeId);

    Long countBlogByTagId(Long tagId);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
