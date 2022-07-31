package com.company.TankGame2_2;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 新开一个线程模拟坦克发射子弹，
 */
public class Shot implements Runnable {
    private int x;   //子弹的x轴
    private int y; //子弹的x轴
    private int Direction = 0;  //子弹的射击方法
    private int speed = 10;     //子弹的射击速度
    boolean isLive = true;    //子弹是否存活的

    //构造器
    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        Direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() { //射击

        while (true) {
            //防止子弹射击速度过快，让她休眠50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向改变子弹的x、y坐标
            switch (Direction) {
                case 0:   //up
                    y -= speed;
                    break;
                case 1:   //right
                    x += speed;
                    break;
                case 2:   //down
                    y += speed;
                    break;
                case 3:   //left
                    x -= speed;
                    break;
            }
            //System.out.println("子弹的x坐标"+x+"  子弹的y坐标"+y);
            //当子弹到达边界（面板的宽度、高度）就应该销毁 （销毁线程）
            //当子弹打中敌人（面板的宽度、高度）就应该销毁 （销毁线程）
            if (!(x >= 0 && x<= 1000 && y >= 0&& y <= 750 && isLive)){
                isLive = false;
                break;
            }
        }
    }
}
