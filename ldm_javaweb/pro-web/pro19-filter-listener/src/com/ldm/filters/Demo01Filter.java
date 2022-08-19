package com.ldm.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
/*
@WebFilter("*.do")
*/

public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HelloA");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("HelloA1");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
