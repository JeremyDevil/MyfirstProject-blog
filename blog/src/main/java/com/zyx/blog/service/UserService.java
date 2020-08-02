package com.zyx.blog.service;

import com.zyx.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
