package com.yl.myblog.dao;

import com.yl.myblog.entity.Tag;
import com.yl.myblog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:54
 * @Version 1.0
 */
public interface TagDao extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
