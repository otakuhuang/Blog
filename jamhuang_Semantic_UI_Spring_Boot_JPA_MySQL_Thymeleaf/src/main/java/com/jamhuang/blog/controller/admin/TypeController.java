package com.jamhuang.blog.controller.admin;

import com.jamhuang.blog.entity.Type;
import com.jamhuang.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String inputType(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            result.rejectValue("name", "nameError", "已存在该分类");
            return "admin/types-input";
        } else {
            Type t2 = typeService.saveType(type);
            if (t2 == null) {
                attributes.addFlashAttribute("message", "增加失败");
            } else {
                attributes.addFlashAttribute("message", "增加成功");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.getTypeById(type.getId());
        if (t == null) {
            attributes.addFlashAttribute("message", "不存在该分类");
        } else {
            Type t2 = typeService.updateType(id, type);
            if (t2 == null) {
                attributes.addFlashAttribute("message", "更新失败");
            } else {
                attributes.addFlashAttribute("message", "更新成功");
            }
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
