package com.yl.myblog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author YeLei
 * @Date 2021/09/18 1:03
 * @Version 1.0
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截路径
        registry.addInterceptor(new LoginInterceptor())
                //拦截admin下的所有路径
                .addPathPatterns("/admin/**")
                //对路径是admin的页面放行
                .excludePathPatterns("/admin")
                //对路径是login的页面放行
                .excludePathPatterns("/admin/login");
        super.addInterceptors(registry);
    }
}
