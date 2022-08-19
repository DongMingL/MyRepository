package com.ldm.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */

/*@WebServlet(urlPatterns = {"/demo01"},
            initParams = {
                @WebInitParam(name = "hello",value = "world"),
                @WebInitParam(name = "我爸",value = "是李刚")
            }
            )*/
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String  initVal = config.getInitParameter("hello");
        System.out.println(initVal);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = " + contextConfigLocation);
    }


}
