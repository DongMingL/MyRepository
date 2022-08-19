package com.ldm.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 *
 */
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo06...");
        //req.getRequestDispatcher("demo07").forward(req,resp);
        resp.sendRedirect("demo7");
    }
}
