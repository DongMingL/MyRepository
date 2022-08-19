package com.ldm.fruit.service.impl;

import com.ldm.fruit.service.FruitService;
import com.ldm.fruit.dao.FruitDAO;
import com.ldm.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class FruitServiceImpl implements FruitService {

    //这个fruitDAO就是.xml里面的property 的 name
    private FruitDAO fruitDAO = null;
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count+5-1)/5 ;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
