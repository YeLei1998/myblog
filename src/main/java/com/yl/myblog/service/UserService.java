package com.yl.myblog.service;

import com.yl.myblog.entity.User;

/**
 * @Author YeLei
 * @Date 2021/09/17 23:12
 * @Version 1.0
 */
public interface UserService {

    User checkUser(String username,String password);
}
