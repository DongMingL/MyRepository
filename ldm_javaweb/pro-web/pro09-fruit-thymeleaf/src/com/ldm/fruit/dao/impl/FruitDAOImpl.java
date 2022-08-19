package com.ldm.fruit.dao.impl;



import com.ldm.fruit.dao.FruitDAO;
import com.ldm.fruit.pojo.Fruit;
import com.ldm.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
