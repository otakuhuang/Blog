package com.jamhuang.blog.controller.admin;

import com.jamhuang.blog.entity.Tag;
import com.jamhuang.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String inputTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag t = tagService.getTagByName(tag.getName());
        if (t != null) {
            result.rejectValue("name", "nameError", "已存在该分类");
            return "admin/tags-input";
        } else {
            Tag t2 = tagService.saveTag(tag);
            if (t2 == null) {
                attributes.addFlashAttribute("message", "增加失败");
            } else {
                attributes.addFlashAttribute("message", "增加成功");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag t = tagService.getTagById(tag.getId());
        if (t == null) {
            attributes.addFlashAttribute("message", "不存在该分类");
        } else {
            Tag t2 = tagService.updateTag(id, tag);
            if (t2 == null) {
                attributes.addFlashAttribute("message", "更新失败");
            } else {
                attributes.addFlashAttribute("message", "更新成功");
            }
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
