package com.ldm.fruit.controller;

import com.ldm.fruit.service.FruitService;
import com.ldm.fruit.dao.FruitDAO;
import com.ldm.fruit.dao.impl.FruitDAOImpl;
import com.ldm.fruit.pojo.Fruit;
import com.ldm.myssm.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */

public class fruitController {

    private FruitService fruitService = null;
    //更新业务
    private String  update(Integer fid,String fname, Integer price,Integer fcount, String remark) {
        //3.执行更新
        fruitService.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));

        //返回一个字符串
       return "redirect:fruit.do";
    }
    //修改业务
    private String  edit(Integer fid,HttpServletRequest request) {

        if(fid !=null){

            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            //super.processTemplate("edit",request,response);
            return "edit";
        }
        return "error";
    }
    //删除业务
    private String del(Integer fid  ){
        if(fid!=null){
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }
    //添加业务
    private String  add(Integer fid,String fname, Integer price,Integer fcount, String remark) {

        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitService.addFruit(fruit);
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }
    private String  index(String oper,String keyword,Integer pageNo,HttpServletRequest request){

        //保存到session作用域
        HttpSession session = request.getSession() ;
        if (pageNo ==null){
            pageNo = 1;
        }
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper) ){
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }
        //重新更新当前页面的值
        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO = new FruitDAOImpl();
        //用list保存查询数据库中的结果
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);
        //总记录条数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
}
