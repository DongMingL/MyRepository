package com.company.TankGame2_1;

import java.util.Vector;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 敌人的坦克
 * 实现Runnable接口，让敌人也能自由移动
 */
public class EnemyHero extends Tank implements Runnable {
    //在敌人坦克类用Vector保存射击动作
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public EnemyHero(int x, int y,int speed) {
        super(x, y,speed);
    }

    @Override
    public void run() {

        while (true) {

                //这里判断 如果shots size（） 《1
                if ( isLive &&shots.size() < 2){

                    Shot s = null ;
                    switch (getDirection()){   //不同的方法射出子弹
                        case 0 :  //up
                            s =new Shot(getX()+20,getY(),0);
                            break;
                        case 1:   //right
                            s =new Shot(getX()+60,getY()+20,1);
                            break;
                        case 2:   //down
                            s =new Shot(getX()+20,getY()+60,2);
                            break;
                        case 3:   //left
                            s =new Shot(getX(),getY()+20,3);
                            break;
                    }
                    shots.add(s);
                    //启动线程
                    new Thread(s).start();

                }

            //根据坦克的方向继续移动
            switch (getDirection()) {
                case 0:   //up
                    for (int i = 0; i < (30); i++) {
                        if ((getY() > 0)) {
                            moveUp();
                        }

                        //休眠50ms

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:   //right
                    for (int i = 0; i < (30); i++) {
                        if ((getX() + 60 < 1000)) {
                            moveRight();
                        }

                        //休眠50ms

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:   //down
                    for (int i = 0; i < (30); i++) {
                        if ((getY()+60 < 750)) {
                            moveDown();
                        }
                        //休眠50ms

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:   //left
                    for (int i = 0; i < (30); i++) {
                        if ((getX()> 0)) {
                            moveLeft();
                        }
                        //休眠50ms

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //休眠50ms

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //随机改变坦克的方向 0-3
            setDirection((int)(Math.random()*4));
            //写多线程，一定要考虑清楚，该线程什么时候结束
            if ((!isLive)) {
                break; //退出线程
            }
        }
    }
}
