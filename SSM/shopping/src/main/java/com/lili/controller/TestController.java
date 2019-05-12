package com.lili.controller;

import com.lili.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    User user;
    @RequestMapping(value = "/login")
    public User userLogin() {
        user.setId(1);
        user.setUsername("zhangsan");
        return user;
    }
}
