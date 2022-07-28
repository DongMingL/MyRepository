package com.company.TankGame2_1;

import java.util.Vector;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 自己的坦克
 */
public class Hero extends Tank {

    //定义一个Shot对象
    Shot shot = null;

    //发射多个子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y,  int speed) {
        super(x, y,speed);
    }

    //定义一个射击动作
    public void shotEnemyTank(){
        if ( shots.size()  == 5){
            return;
        }
        //创建shot对象,与己方坦克的位置和方向有关
        //选定子弹发射的方向
        switch (getDirection()) { //得到Hero对象的方向
            case 0:    //up
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:    //right
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:    //down
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3:    //left
                shot = new Shot(getX(),getY()+20,3);
                break;
        }

        //把创建的shot添加到shots集合中
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();

    }
}
