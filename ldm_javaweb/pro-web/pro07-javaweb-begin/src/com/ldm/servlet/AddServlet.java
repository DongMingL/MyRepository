package com.ldm.servlet;

import com.ldm.fruit.dao.impl.FruitDAOImpl;
import com.ldm.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 */
public class AddServlet extends HttpServlet {


    //处理提交的post请求
    //注意 post方法下 要设置编码 防止中文乱码
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        //注意 get方式下是不需要设置utf-8（基于tomcat8）
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        //注意：
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");
//        System.out.println("fname="+fname);
//        System.out.println("price="+price);
//        System.out.println("fcount="+fcount);
//        System.out.println("remark="+remark);

        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(null, fname, price, fcount, remark));
        System.out.println(flag ? "成功" : "失败");


    }
}
