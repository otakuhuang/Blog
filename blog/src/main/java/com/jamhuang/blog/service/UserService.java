package com.jamhuang.blog.service;

import com.jamhuang.blog.entity.User;

public interface UserService {

    User checkUser(String username, String password);
}
