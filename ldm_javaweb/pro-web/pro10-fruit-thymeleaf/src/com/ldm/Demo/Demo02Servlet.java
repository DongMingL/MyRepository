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
@WebServlet("/demo02")
public class Demo02Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取保存的数据，key 为 username
        Object username = req.getAttribute("username");
        System.out.println(username);

    }
}
