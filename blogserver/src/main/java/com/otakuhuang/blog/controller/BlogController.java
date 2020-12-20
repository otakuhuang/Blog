package com.otakuhuang.blog.controller;

import com.otakuhuang.blog.common.ResultVO;
import com.otakuhuang.blog.entity.Blog;
import com.otakuhuang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public ResultVO<Map<String, Object>> getBlog(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        return new ResultVO<>(blogService.selectBlogByPage(currentPage));
    }

    @GetMapping("/blog/{id}")
    public Blog getBlogById(@PathVariable("id") long id) {
        return blogService.selectBlogById(id);
    }
}
