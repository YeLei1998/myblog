package com.yl.myblog.web;

import com.yl.myblog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author YeLei
 * @Date 2021/09/14 12:13
 * @Version 1.0
 */

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id,@PathVariable String name){
//        int i = 9/0;
//        String blog = null;
//        if (blog == null){
//            throw  new NotFoundException("博客不存在！");
//        }

        System.out.println("-------index-------");
        return "index";
    }
}
