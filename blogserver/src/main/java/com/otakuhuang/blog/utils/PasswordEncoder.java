package com.otakuhuang.blog.utils;

import com.otakuhuang.blog.entity.User;
import org.springframework.util.DigestUtils;

import java.util.Objects;

public class PasswordEncoder {
    public static String passwordEncoder(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static boolean passwordMatch(User user, String password) {
        return Objects.equals(user.getPassword(), DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
