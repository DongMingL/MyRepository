package com.company.TankGame2_1;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 炸弹
 */
public class Bomb {
    int x, y;  //炸弹的xy轴
    int life = 9;  //炸弹的生命周期
    boolean isLive = true;  //是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少Bomb的生命周期
    public void lifeDown(){  //配合图片出现爆炸效果
        if ((life > 0)) {
            life--;
        }else {
            isLive = false;
        }
    }
}
