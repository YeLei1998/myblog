package com.yl.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author YeLei
 * @Date 2021/09/14 12:13
 * @Version 1.0
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i = 9/0;
//        String blog = null;
//        if (blog == null){
//            throw  new NotFoundException("博客不存在！");
//        }

        System.out.println("-------index-------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        System.out.println("-------index-------");
        return "admin/blogs-input";
    }
}
