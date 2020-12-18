package com.otakuhuang.blog.controller;

import com.otakuhuang.blog.entity.Blog;
import com.otakuhuang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog")
    public List<Blog> getBlogById() {
        return blogService.selectAllBlog();
    }

    @GetMapping("/blog/{id}")
    public Blog getBlogById(@PathVariable("id") long id) {
        return blogService.selectBlogById(id);
    }

    @PostMapping("/blog")
    public ResponseEntity<List<Blog>> addBlog(@RequestBody Blog blog) {
        return null;
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<List<Blog>> deleteBlogById(@PathVariable("id") long id) {
        return null;
    }
}
