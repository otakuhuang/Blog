package com.otakuhuang.blog.controller;

import com.otakuhuang.blog.common.ResultVO;
import com.otakuhuang.blog.entity.User;
import com.otakuhuang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<String> register(User user) {
        int result = userService.registerUser(user);
        if (result == 0) {
            return new ResultVO<>("注册成功");
        } else if (result == 1) {
            return new ResultVO<>("注册失败，用户名重复!");
        } else {
            return new ResultVO<>("注册失败");
        }
    }

    @GetMapping("/login_page")
    public ResultVO<String> loginPage() {

        return new ResultVO<>("请先登录!");
    }

    @PostMapping("/login")
    public ResultVO<String> login() {
        return new ResultVO<>("");
    }

    @GetMapping("/login_error")
    public ResultVO<String> loginError() {
        return new ResultVO<>("登录失败!");
    }

    @GetMapping("/login_success")
    public ResultVO<String> loginSuccess() {
        return new ResultVO<>("登录成功!");
    }


}
