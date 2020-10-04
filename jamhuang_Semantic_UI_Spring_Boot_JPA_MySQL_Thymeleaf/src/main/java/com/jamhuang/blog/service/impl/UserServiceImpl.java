package com.jamhuang.blog.service.impl;

import com.jamhuang.blog.dao.UserRepository;
import com.jamhuang.blog.entity.User;
import com.jamhuang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
