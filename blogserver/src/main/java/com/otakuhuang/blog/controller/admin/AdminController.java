package com.otakuhuang.blog.controller.admin;

import com.otakuhuang.blog.common.ResultVO;
import com.otakuhuang.blog.entity.Blog;
import com.otakuhuang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/blog")
    public ResultVO<String> addBlog(@RequestBody @Valid Blog blog) {
        return new ResultVO<>(blogService.addBlog(blog));
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<List<Blog>> deleteBlogById(@PathVariable("id") long id) {
        return null;
    }
}
