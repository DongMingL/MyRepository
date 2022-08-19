package com.ldm.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把定义好的那张网加进来
        registry.addInterceptor(new LoginInterceptor())
                //加上要过滤哪些路径,拦截所有访问admin的
                .addPathPatterns("/admin/**")
                //不拦截admin和admin/login
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

}
