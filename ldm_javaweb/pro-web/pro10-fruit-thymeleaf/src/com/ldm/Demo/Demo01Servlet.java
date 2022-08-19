package com.ldm.Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 *
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向request保存作用域
        req.setAttribute("username","lili");
        //客户端重定向
        //resp.sendRedirect("demo02");
        //服务器内部转发
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
