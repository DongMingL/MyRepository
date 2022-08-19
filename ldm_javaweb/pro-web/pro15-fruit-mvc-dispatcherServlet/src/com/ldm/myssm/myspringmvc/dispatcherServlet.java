package com.ldm.myssm.myspringmvc;


import com.ldm.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
@WebServlet("*.do")
public class dispatcherServlet  extends HttpServlet {

    private Map<String,Object> beanMap = new HashMap<>();

    public dispatcherServlet() {
    }
    public void init(){

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationController.xml");
            //1、创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2、创建DocumentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //获取document 对象
            Document document  = documentBuilder.parse(inputStream);

            //获取所有Bean的节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class controllerBeanClass =Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();
                    Method setServletContext = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContext.invoke(beanObj,this.getServletContext());


                    beanMap.put(beanId,beanObj);

                }
            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止中文乱码
        request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String  servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);

        Object controllerObj = beanMap.get(servletPath);


        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate  = "index";
        }

        try {
            Method method = controllerObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null){
                method.setAccessible(true);
                method.invoke(controllerObj,request,response);
            }else {
                throw new RuntimeException("operate 的值非法");

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
