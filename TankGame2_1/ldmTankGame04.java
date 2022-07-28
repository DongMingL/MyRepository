package com.company.TankGame2_1;

import javax.swing.*;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 *主线程
 */
public class ldmTankGame04 extends JFrame {
    //定义自己的画板
    private TankPenal tp= null;

    public static void main(String[] args) {

        new ldmTankGame04();

    }
    public ldmTankGame04(){
        tp = new TankPenal();
        this.add(tp);//把面板（游戏的绘图区）加入窗口
        //将tp放入到Thread，并启动
        final Thread thread = new Thread(tp);
        thread.start();
        this.setSize(1200,950);
        //添加键盘事件监听
        this.addKeyListener(tp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
