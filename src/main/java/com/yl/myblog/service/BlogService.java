package com.yl.myblog.service;

import com.yl.myblog.entity.Blog;
import com.yl.myblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author YeLei
 * @Date 2021/09/19 9:38
 * @Version 1.0
 */
public interface BlogService {

    Blog addBlog(Long id);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Blog saveBlog(Blog blog);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
}
