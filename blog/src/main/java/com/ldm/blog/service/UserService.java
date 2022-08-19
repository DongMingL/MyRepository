package com.ldm.blog.service;

import com.ldm.blog.po.User;

public interface UserService {

    User checkUser(String username, String password);

}
