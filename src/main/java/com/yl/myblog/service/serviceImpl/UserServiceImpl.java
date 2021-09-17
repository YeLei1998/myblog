package com.yl.myblog.service.serviceImpl;

import com.yl.myblog.dao.UserDao;
import com.yl.myblog.entity.User;
import com.yl.myblog.service.UserService;
import com.yl.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author YeLei
 * @Date 2021/09/17 23:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
