package com.ldm.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    //预处理
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //session为空，就是未登陆
        if(request.getSession().getAttribute("user") == null){
            //重定向到登陆页面
            response.sendRedirect("/admin");
            return false;
        }
        //session不为空，就继续执行下去就行了
        return true;
    }
}
