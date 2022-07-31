package com.company.TankGame2_2;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 *主线程
 */
public class ldmTankGame05 extends JFrame {
    //定义自己的画板
    private TankPenal tp= null;
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {


        new ldmTankGame05();

    }
    public ldmTankGame05(){
        System.out.println("请输入选择 1 ：新游戏 2：上一局游戏");
        String key = myScanner.next();
        tp = new TankPenal(key);
        this.add(tp);//把面板（游戏的绘图区）加入窗口
        //将tp放入到Thread，并启动
        Thread thread = new Thread(tp);
        thread.start();
        this.setSize(1300,950);
        //添加键盘事件监听
        this.addKeyListener(tp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame中增加相应的关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
            }
        });
    }
}
