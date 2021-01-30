package com.layfolk.chat.service;

import com.layfolk.chat.pojo.User;

public interface UserService {
    public User queryUserByName(String username);
}
