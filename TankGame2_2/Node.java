package com.company.TankGame2_2;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 一个Node对象就是 一个敌人坦克的信息
 *
 */


public class Node {
    private int x;
    private int y;
    private int directory;

    public Node(int x, int y, int directory) {
        this.x = x;
        this.y = y;
        this.directory = directory;
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

    public int getDirectory() {
        return directory;
    }

    public void setDirectory(int directory) {
        this.directory = directory;
    }


}
