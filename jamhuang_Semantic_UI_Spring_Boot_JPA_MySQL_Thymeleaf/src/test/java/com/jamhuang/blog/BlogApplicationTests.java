package com.jamhuang.blog;

import com.jamhuang.blog.dao.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.DigestUtils;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void contextLoads() {
        String pwd = "LswR$B3bt9vQHcs#";
        System.out.println(DigestUtils.md5DigestAsHex(pwd.getBytes()));
    }
}
