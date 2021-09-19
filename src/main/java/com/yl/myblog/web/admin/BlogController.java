package com.yl.myblog.web.admin;

import com.yl.myblog.entity.Blog;
import com.yl.myblog.service.BlogService;
import com.yl.myblog.service.TypeService;
import com.yl.myblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YeLei
 * @Date 2021/09/18 0:53
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    /**
     * 根据更新时间，倒序分页排序
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size=5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        //templates 局部刷新,类似ajax,blogList需要在前端页面定义
        return "admin/blogs :: blogList";
    }
}
