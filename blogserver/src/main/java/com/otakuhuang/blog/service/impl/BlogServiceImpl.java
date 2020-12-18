package com.otakuhuang.blog.service.impl;

import com.otakuhuang.blog.entity.Blog;
import com.otakuhuang.blog.dao.BlogMapper;
import com.otakuhuang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog selectBlogById(long id) {
        return blogMapper.findBlogById(id);
    }

    @Override
    public List<Blog> selectAllBlog() {
        return blogMapper.findAllBlog();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyBlogById(Blog blog) {
        blogMapper.updateBlogById(blog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeBlogById(long id) {
        blogMapper.deleteBlogById(id);
    }
}
