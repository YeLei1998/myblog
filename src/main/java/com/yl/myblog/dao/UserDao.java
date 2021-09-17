package com.yl.myblog.dao;

import com.yl.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YeLei
 * @Date 2021/09/17 23:17
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
