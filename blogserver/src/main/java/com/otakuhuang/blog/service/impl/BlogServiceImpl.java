package com.otakuhuang.blog.service.impl;

import com.otakuhuang.blog.common.APIException;
import com.otakuhuang.blog.entity.Blog;
import com.otakuhuang.blog.mapper.BlogMapper;
import com.otakuhuang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog selectBlogById(long id) {
        Blog blog = blogMapper.findBlogById(id);
        if (Objects.isNull(blog)) {
            throw new APIException(2002, "博客不可访问或已删除");
        }
        return blog;
    }

    @Override
    public List<Blog> selectAllBlog() {
        return blogMapper.findAllBlog();
    }

    @Override
    public Map<String, Object> selectBlogByPage(int currentPage) {
        Map<String, Object> map = new HashMap<>(3);
        int pageSize = 2;
        int totalPages = blogMapper.getBlogCount() / pageSize;
        List<Blog> blogs = blogMapper.findBlogByPage((currentPage - 1) * pageSize, pageSize);

        if (blogs.isEmpty()) {
            throw new APIException(2001, "页面获取失败");
        }

        map.put("currentPage", currentPage);
        map.put("totalPages", totalPages);
        map.put("blogs", blogs);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addBlog(Blog blog) {
        blogMapper.insertBlog(blog);

        String status = "博客添加成功";

        if (blog.isSave()) {
            status = "博客保存为草稿";
        }

        if (blog.isPublished()) {
            status = "博客发布成功";
        }

        return status;
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
