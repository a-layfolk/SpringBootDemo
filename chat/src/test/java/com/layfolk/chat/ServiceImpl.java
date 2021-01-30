package com.layfolk.chat;

import com.layfolk.chat.pojo.User;
import com.layfolk.chat.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceImpl {

    @Autowired
    UserServiceImpl userService;
    @Test
    public void queryUserByName() {
        User user = userService.queryUserByName("赵一");
        System.out.println(user);
    }
}
