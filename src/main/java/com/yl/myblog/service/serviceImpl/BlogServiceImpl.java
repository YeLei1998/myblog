package com.yl.myblog.service.serviceImpl;

import com.yl.myblog.NotFoundException;
import com.yl.myblog.dao.BlogDao;
import com.yl.myblog.entity.Blog;
import com.yl.myblog.entity.Type;
import com.yl.myblog.service.BlogService;
import com.yl.myblog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/19 9:43
 * @Version 1.0
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog addBlog(Long id) {
        return blogDao.findOne(id);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogDao.findOne(id);
        if (b == null){
            throw new NotFoundException("不存在该博客！");
        }
        BeanUtils.copyProperties(b,blog);
        return blogDao.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.delete(id);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {

        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //如果标题不为" " 且 标题 不为 null
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%" + blog.getTitle() + "%"));
                }
                //如果分类id 不为 null
                if (blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                //如果为推荐文章
                if (blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }
}
