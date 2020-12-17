package com.jamhuang.blog.controller;

import com.jamhuang.blog.entity.Type;
import com.jamhuang.blog.service.BlogService;
import com.jamhuang.blog.service.TypeService;
import com.jamhuang.blog.vo.BlogQuery;
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
public class TypePageController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,
                        Model model) {
        List<Type> types = typeService.listTypeTop(Integer.MAX_VALUE);

        if (types.size() == 0) {
            id = -1L;
        } else if (id == -1) {
            id = types.get(0).getId();
        }

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
