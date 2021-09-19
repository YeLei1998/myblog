package com.yl.myblog.dao;

import com.yl.myblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author YeLei
 * @Date 2021/09/19 9:43
 * @Version 1.0
 */
public interface BlogDao extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {
}
