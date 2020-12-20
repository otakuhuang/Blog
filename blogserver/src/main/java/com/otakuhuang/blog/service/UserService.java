package com.otakuhuang.blog.service;

import com.otakuhuang.blog.entity.User;

public interface UserService {
    User loadUserByUsername(String username);

    int registerUser(User user);
}
