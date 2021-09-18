package com.yl.myblog.dao;

import com.yl.myblog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:54
 * @Version 1.0
 */
public interface TypeDao extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
