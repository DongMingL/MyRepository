package com.company.TankGame2_1;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 坦克的属性
 */
public class Tank {
    private int x;  //坦克x坐标
    private int y;  //坦克y坐标
    private int direction = 0;  //坦克的方向  0up 1down 2right 3left
    private int speed = 1;
    boolean isLive = true;

    public Tank(int x, int y,int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;

    }

    //定义坦克移动的各种方向
    public void moveUp(){
        y  -= speed;
    }
    public void moveRight(){
        x  += speed;
    }
    public void moveDown(){
        y  += speed;
    }
    public void moveLeft(){
        x  -= speed;
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
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
