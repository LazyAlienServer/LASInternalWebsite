package com.las.lasbackenduser3000.service.db.impl;

import com.las.lasbackenduser3000.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    public UserServiceImpl userService;

    @Test
    void addUser() {
        User user = new User(0,100,"Martin","Martin","苦逼开发","developer","1222","1",1,"0",1,-100);
        int addUser = userService.addUser(user);
        assert addUser == 1;
    }

    @Test
    void getUserList() {
        System.out.println(userService.getUserList());

    }

    @Test
    void getUserByTag() {
        System.out.println(userService.getUserByOnline(1));
    }

    @Test
    void getUserById() {
        System.out.println(userService.getUserById(4));
    }

    @Test
    void getUserByName() {
        System.out.println(userService.getUserByName("Martin"));
    }

    @Test
    void getUserByPassword() {
        System.out.println(userService.getUserByPassword("Martin"));
    }

    @Test
    void getUserByTime() {
        System.out.println(userService.getUserByTime(100));
    }

    @Test
    void getUserByLastLogin() {
        System.out.println(userService.getUserByLastLogin("1222"));
    }

    @Test
    void getUserByWhiteList() {
        System.out.println(userService.getUserByWhiteList("1"));
    }

    @Test
    void getUserByOnline() {
        System.out.println(userService.getUserByOnline(1));
    }

    @Test
    void getUserByProbation() {
        System.out.println(userService.getUserByProbation("0"));
    }

    @Test
    void getUserByAdministrator(){
        System.out.println(userService.getUserByAdministrator(1));
    }

    @Test
    void deleteUserByName() {
        System.out.println(userService.deleteUserByName("Martin"));
    }
}