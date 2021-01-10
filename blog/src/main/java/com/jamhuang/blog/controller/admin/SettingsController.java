package com.jamhuang.blog.controller.admin;

import com.jamhuang.blog.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/settings")
    public String settingsPage(@PageableDefault(size = 10) Pageable pageable,
                               Model model) {
        model.addAttribute("page", settingsService.settingsList(pageable));
        return "admin/settings";
    }
}
