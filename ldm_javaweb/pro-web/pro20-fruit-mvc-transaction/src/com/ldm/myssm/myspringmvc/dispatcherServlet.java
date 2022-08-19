package com.ldm.myssm.myspringmvc;


import com.ldm.myssm.ioc.BeanFactory;
import com.ldm.myssm.ioc.ClassPathXmlApplicationContext;
import com.ldm.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
@WebServlet("*.do")
public class dispatcherServlet  extends ViewBaseServlet {

    private BeanFactory beanFactory;


    public dispatcherServlet() {
    }
    public void init() throws ServletException {

        super.init();
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null) {
            beanFactory = (BeanFactory)beanFactoryObj;
        }else {
            throw new RuntimeException("ioc容器获取失败！");
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止中文乱码
        // request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String  servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);

        Object controllerBeanObj = beanFactory.getBean(servletPath);


        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate  = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())){
                    //1、统一获取请求参数

                    //获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //parametersValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了
                        if ("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }else if ("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }else {
                            //从请求中获取参数值
                            String  parameterValue = request.getParameter(parameterName);
                            String  typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;

                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }

                    }
                    //2、controller组件的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj,parameterValues);

                    //3、视图处理
                    String methodReturnStr = (String)returnObj;
                    if (methodReturnStr.startsWith("redirect:")){
                        String  redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,request,response);
                    }

                }
            }

           /* }else {
                throw new RuntimeException("operate 的值非法");

            }*/
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
// 常见错误： IllegalArgumentException: argument type mismatch