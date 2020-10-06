package com.jamhuang.blog.controller;

import com.jamhuang.blog.entity.Tag;
import com.jamhuang.blog.service.BlogService;
import com.jamhuang.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagPageController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 1, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id,
                       Model model) {
        List<Tag> tags = tagService.listTagTop(Integer.MAX_VALUE);

        if (tags.size() == 0) {
            id = -1L;
        } else if (id == -1) {
            id = tags.get(0).getId();
        }

        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("tags", tags);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
