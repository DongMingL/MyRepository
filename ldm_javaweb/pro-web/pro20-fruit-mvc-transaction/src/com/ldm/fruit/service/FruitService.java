package com.ldm.fruit.service;

import com.ldm.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
public interface FruitService {
    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword , Integer pageNo);
    //添加库存记录信息
    void addFruit(Fruit fruit);
    //根据id查看指定库存记录
    Fruit getFruitByFid(Integer fid);
    //删除特定库存记录
    void delFruit(Integer fid);
    //获取总页数
    Integer getPageCount(String keyword);
    //修改特定库存记录
    void updateFruit(Fruit fruit);
}
