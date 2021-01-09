package com.book.test;

import com.book.bean.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","222222","bbj168@168.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login("bbj168","222222"));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
    }
}