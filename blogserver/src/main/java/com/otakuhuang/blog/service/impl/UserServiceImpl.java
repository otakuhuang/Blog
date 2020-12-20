package com.otakuhuang.blog.service.impl;

import com.otakuhuang.blog.entity.User;
import com.otakuhuang.blog.mapper.UserMapper;
import com.otakuhuang.blog.service.UserService;
import com.otakuhuang.blog.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return new User();
        }
        return user;
    }

    /**
     * 用户注册
     * @param user 用户对象
     * @return 0: 注册成功 1: 存在该用户，注册失败 2: 注册失败
     */
    @Override
    public int registerUser(User user) {
        User loadUserByUsername = userMapper.getUserByUsername(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        user.setPassword(PasswordEncoder.passwordEncoder(user.getPassword()));
        user.setEnabled(true);
        int result = userMapper.addUser(user);
        if (result == 1) {
            return 0;
        }
        return 2;
    }
}
