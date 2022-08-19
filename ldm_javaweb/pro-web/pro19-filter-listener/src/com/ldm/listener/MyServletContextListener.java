package com.ldm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
@WebListener
public class MyServletContextListener implements ServletContextListener  {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContextListener.super.contextInitialized(sce);
        System.out.println("当前的Servlet上下文对象的初始化动作被我监听到了。。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("当前的Servlet上下文对象的销毁的动作被我监听到了。。。。");
    }
}
