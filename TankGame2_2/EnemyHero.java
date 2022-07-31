package com.company.TankGame2_2;

import java.util.Vector;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 敌人的坦克
 * 实现Runnable接口，让敌人也能自由移动
 */
@SuppressWarnings({"all"})
public class EnemyHero extends Tank implements Runnable {
    //在敌人坦克类用Vector保存射击动作
    Vector<Shot> shots = new Vector<>();
    //增加成员，enemyHero 可以得到敌人坦克的Vector

    Vector<EnemyHero> enemyHeroes = new Vector<>();

    boolean isLive = true;
    public EnemyHero(int x, int y,int speed) {
        super(x, y,speed);
    }

    //这里提供一个方法，可以将TankPanel的成员Vector<EnemyHero> enemyHeroes = new Vector<>();
    //设置到EnemyHero 的成员 eHeroes
    public void setEnemyHeroes(Vector<EnemyHero> enemyHeroes) {
        this.enemyHeroes = enemyHeroes;
    }

    /**
     * 编写方法，判断当前坦克，是否和Vector<EnemyHero>里面的坦克重叠
     */
    public boolean isTouchEnemyHero(){
        //判断当前敌人坦克（this）方向
        switch (this.getDirection()){
            case 0:  //up
                //让当前坦克和其他坦克比较
                for (int i = 0; i < enemyHeroes.size(); i++) {
                    //从Vector中取出一个敌人坦克与当前坦克比较
                    EnemyHero enemyHero = enemyHeroes.get(i);
                    //不和自己比较
                    if (enemyHero != this){
                        //如果敌人坦克是上下方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 40]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 60]
                        if (enemyHero.getDirection() == 0 || enemyHero.getDirection() == 2){
                            //2、当前敌人坦克的左上角坐标是[this.getX(), this.getY()]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 40
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 60){
                                return true;
                            }
                            //3、当前敌人坦克的右上角坐标是[this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyHero.getX()
                                    && this.getX() + 40 <= enemyHero.getX() + 40
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 60){
                                return true;
                            }

                        }
                        //如果敌人坦克是在右左方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 60]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 40]
                        if (enemyHero.getDirection() == 1 || enemyHero.getDirection() == 3){
                            //4、当前敌人坦克的左上角坐标是[this.getX(), this.getY()]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 60
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 40){
                                return true;
                            }
                            //5、当前敌人坦克的右上角坐标是[this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyHero.getX()
                                    && this.getX() + 40 <= enemyHero.getX() + 60
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 40){
                                return true;
                            }

                        }
                    }

                }
                break;
            case 1:   //right
                //让当前坦克和其他坦克比较
                for (int i = 0; i < enemyHeroes.size(); i++) {
                    //从Vector中取出一个敌人坦克与当前坦克比较
                    EnemyHero enemyHero = enemyHeroes.get(i);
                    //不和自己比较
                    if (enemyHero != this){
                        //如果敌人坦克是上下方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 40]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 60]
                        if (enemyHero.getDirection() == 0 || enemyHero.getDirection() == 2){
                            //2、当前敌人坦克的右上角坐标是[this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 40
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 60){
                                return true;
                            }
                            //3、当前敌人坦克的右下角坐标是[this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 40
                                    && this.getY() + 40 >= enemyHero.getY()
                                    && this.getY() + 40 <= enemyHero.getY() + 60){
                                return true;
                            }

                        }
                        //如果敌人坦克是在右/左方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 60]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 40]
                        if (enemyHero.getDirection() == 1 || enemyHero.getDirection() == 3){
                            //4、当前敌人坦克的右上角坐标是[this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 60
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 40){
                                return true;
                            }
                            //5、当前敌人坦克的右下角坐标是[this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 60
                                    && this.getY() + 40 >= enemyHero.getY()
                                    && this.getY() + 40 <= enemyHero.getY() + 40){
                                return true;
                            }

                        }
                    }

                }
                break;
            case 2:   //down
                //让当前坦克和其他坦克比较
                for (int i = 0; i < enemyHeroes.size(); i++) {
                    //从Vector中取出一个敌人坦克与当前坦克比较
                    EnemyHero enemyHero = enemyHeroes.get(i);
                    //不和自己比较
                    if (enemyHero != this){
                        //如果敌人坦克是上下方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 40]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 60]
                        if (enemyHero.getDirection() == 0 || enemyHero.getDirection() == 2){
                            //2、当前敌人坦克的左下角坐标是[this.getX(), this.getY() + 60 ]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 40
                                    && this.getY() +60 >= enemyHero.getY()
                                    && this.getY() +60 <= enemyHero.getY() + 60){
                                return true;
                            }
                            //3、当前敌人坦克的右下角坐标是[this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyHero.getX()
                                    && this.getX() + 40 <= enemyHero.getX() + 40
                                    && this.getY() + 60 >= enemyHero.getY()
                                    && this.getY() + 60 <= enemyHero.getY() + 60){
                                return true;
                            }

                        }
                        //如果敌人坦克是在右左方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 60]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 40]
                        if (enemyHero.getDirection() == 1 || enemyHero.getDirection() == 3){
                            //4、当前敌人坦克的左上角坐标是[this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 60
                                    && this.getY() + 60 >= enemyHero.getY()
                                    && this.getY() + 60 <= enemyHero.getY() + 40){
                                return true;
                            }
                            //5、当前敌人坦克的右上角坐标是[this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyHero.getX()
                                    && this.getX() + 40 <= enemyHero.getX() + 60
                                    && this.getY() + 60 >= enemyHero.getY()
                                    && this.getY() + 60 <= enemyHero.getY() + 40){
                                return true;
                            }

                        }
                    }

                }
                break;
            case 3:   //left
                //让当前坦克和其他坦克比较
                for (int i = 0; i < enemyHeroes.size(); i++) {
                    //从Vector中取出一个敌人坦克与当前坦克比较
                    EnemyHero enemyHero = enemyHeroes.get(i);
                    //不和自己比较
                    if (enemyHero != this){
                        //如果敌人坦克是上下方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 40]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 60]
                        if (enemyHero.getDirection() == 0 || enemyHero.getDirection() == 2){
                            //2、当前敌人坦克的左上角坐标是[this.getX(), this.getY()]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 40
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 60){
                                return true;
                            }
                            //3、当前敌人坦克的左下角坐标是[this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyHero.getX()
                                    && this.getX() <= enemyHero.getX() + 40
                                    && this.getY() + 40 >= enemyHero.getY()
                                    && this.getY() + 40 <= enemyHero.getY() + 60){
                                return true;
                            }

                        }
                        //如果敌人坦克是在右/左方向
                        //1、敌人坦克x坐标的范围是      [enemyHero.getX(), enemyHero.getX() + 60]
                        //         y                [enemyHero.getY(), enemyHero.getY() + 40]
                        if (enemyHero.getDirection() == 1 || enemyHero.getDirection() == 3){
                            //4、当前敌人坦克的左上角坐标是[this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 60
                                    && this.getY() >= enemyHero.getY()
                                    && this.getY() <= enemyHero.getY() + 40){
                                return true;
                            }
                            //5、当前敌人坦克的左下角坐标是[this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyHero.getX()
                                    && this.getX() + 60 <= enemyHero.getX() + 60
                                    && this.getY() + 40 >= enemyHero.getY()
                                    && this.getY() + 40 <= enemyHero.getY() + 40){
                                return true;
                            }

                        }
                    }

                }
                break;
        }
        return  false;
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
                        if (getY() > 0 && !isTouchEnemyHero() && !isTouchEnemyHero()) {
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
                        if ((getX() + 60 < 1000) && !isTouchEnemyHero()) {
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
                        if ((getY()+60 < 750) && !isTouchEnemyHero()) {
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
