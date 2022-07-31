package com.company.TankGame2_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 *
 */
@SuppressWarnings({"all"})
//为了监听键盘事件，实现KeyListener接口
//为了让Penal不停的重绘，需要将TankPanel实现Runnable接口，当作一个线程使用
public class TankPenal extends JPanel implements KeyListener,Runnable{
    //定义自己的坦克
    Hero hero = null;

    //定义敌人的坦克,有多个，放在Vector集合内
    Vector<EnemyHero> eHeroes = new Vector<>();
    //定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    //定义一个Vector，存放我们的炸弹
    //当子弹击中坦克时，加入一个Bomb的对象到bomb
    Vector<Bomb> bombs = new Vector<>();
    int eHeroesSize = 3;
    //定义三张图片显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;


    public TankPenal(String key){
        //开始上局的方法
        nodes = Recorder.getNodesAndAllEnemyTankRec();
        //初始化自己的坦克
        hero = new Hero(400,100,20);
        //将TAnkPenal 的 eHeros 赋值给Record 对象的EnemyHeroes
        Recorder.setEnemyHeroes(eHeroes);

        switch (key){
            case "2" :

                //初始化敌人的坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //创建敌人坦克对象
                    EnemyHero eHero = new EnemyHero(node.getX(), node.getY(),3);
                    //设置方向 enemyHeroes 设置给eHero
                    eHero.setEnemyHeroes(eHeroes);
                    eHero.setDirection(node.getDirectory());
                    //启动敌人坦克移动的线程
                    new Thread(eHero).start();
                    //给坦克添加子弹对象
                    Shot shot = new Shot(eHero.getX()+20,eHero.getY()+60,eHero.getDirection());
                    //加入eHeroes的Vector成员
                    eHero.shots.add(shot);
                    //启动Thread线程
                    new Thread(shot).start();
                    eHeroes.add(eHero);
                }
                break;
            case "1" :  //相当于继续上局游戏
                //初始化敌人的坦克
                for (int i = 0; i < eHeroesSize; i++) {
                    //创建敌人坦克对象
                    EnemyHero eHero = new EnemyHero(100 * (i + 1), 0,3);
                    //设置方向 enemyHeroes 设置给eHero
                    eHero.setEnemyHeroes(eHeroes);
                    eHero.setDirection(2);
                    //启动敌人坦克移动的线程
                    new Thread(eHero).start();
                    //给坦克添加子弹对象
                    Shot shot = new Shot(eHero.getX()+20,eHero.getY()+60,eHero.getDirection());
                    //加入eHeroes的Vector成员
                    eHero.shots.add(shot);
                    //启动Thread线程
                    new Thread(shot).start();
                    eHeroes.add(eHero);
                }
                break;
            default:
                System.out.println("你的输入有误重新输入：");
        }


        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }


    //编写方法，记录我坦克击落敌方坦克数
    public void showInfo(Graphics g){
        //画出玩家的成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("你累计击落敌人的坦克是：" ,1020,30);
        DrawTank(1020,60,g,0,0);//画出一个坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //坦克游戏大战的绘图区 战斗区域
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色

        //调用记录成绩的方法
        showInfo(g);

        //把坦克的绘制用方法封装起来，不让此处代码繁杂(自己的坦克）
        if (hero != null && hero.isLive){
            DrawTank(hero.getX(),hero.getY(),g,hero.getDirection(),0);
        }

        //画出己方坦克射出的子弹
        /*if (hero.shot !=null && hero.shot.isLive){

            //g.fill3DRect(hero.shot.getX(),hero.shot.getY(),10,10,false);
            g.draw3DRect(hero.shot.getX(),hero.shot.getY(),1,1,false);
        }*/
        //己方画出多个子弹的图形
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot !=null && shot.isLive){

                //g.fill3DRect(hero.shot.getX(),hero.shot.getY(),10,10,false);
                g.draw3DRect(shot.getX(),shot.getY(),1,1,false);
                g.setColor(Color.RED);
            }else { //如果该shot对象失效了，就从shots集合取出
                hero.shots.remove(shot);
            }

        }
        //如果bombs集合里面有bomb对象，就画出
        for (int i = 0; i < (bombs.size()); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值画出对应的图片
            if ((bomb.life > 6)) {
               g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);

            }else {
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);

            }
            //让炸弹生命期减少
            bomb.lifeDown();
            //如果bomb.life=0，就从bombs集合删除bomb
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        //敌人的坦克，遍历Vector
        for (int i = 0; i < eHeroes.size(); i++) {
            //在Vector取出坦克
            EnemyHero eHero = eHeroes.get(i);
            //不能直接绘制坦克，需要判断其是否存活
            if (eHero.isLive) {  //当敌人坦克存活才绘敌人坦克
                DrawTank(eHero.getX(), eHero.getY(), g, eHero.getDirection(), 1);

                //画出敌方坦克的所有子弹
                for (int j = 0; j < eHero.shots.size(); j++) {
                    //取出子弹
                    Shot shot = eHero.shots.get(j);
                    //绘制子弹
                    if ((shot.isLive)) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                        g.setColor(Color.yellow);
                    } else {
                        //从Vector移除子弹
                        eHero.shots.remove(shot);
                    }
                }
            }

        }


    }
    //编写方法，绘制出自己的tank

    /**
     *
     * @param x  坦克的x坐标
     * @param y  坦克的y坐标
     * @param g  画图工具 就是一个画笔
     * @param direction  坦克以后会调转方向
     * @param type       会有敌人坦克，所以定义类型
     */
    public void DrawTank(int x,int y, Graphics g, int direction, int type){

        //根据不同的类型画出敌我坦克
        switch (type) {
            case 0: //自己的坦克
                g.setColor(Color.cyan);
                break;
            case 1:  //敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克调转方向，来绘制不同方向的坦克
        //direction 0:向上 1：向右 2：向下  3:向左
        switch (direction) {
            case 0:  //0表示坦克向上
                g.fill3DRect(x,y,10,60,false);//画出坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克中间的矩形
                g.fillOval(x+10,y+20,20,20);//坦克中间的盖子
                g.drawLine(x+20,y+30,x+20,y);//坦克中间的炮筒
                g.setColor(Color.cyan);
                break;
            case 1:  //1表示坦克向右
                g.fill3DRect(x,y,60,10,false);//画出坦克左边的轮子
                g.fill3DRect(x,y+30,60,10,false);//画出坦克右边边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克中间的矩形
                g.fillOval(x+20,y+10,20,20);//坦克中间的盖子
                g.drawLine(x+30,y+20,x+60,y+20);//坦克中间的炮筒
                g.setColor(Color.cyan);
                break;
            case 2:  //2表示坦克向下
                g.fill3DRect(x,y,10,60,false);//画出坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克中间的矩形
                g.fillOval(x+10,y+20,20,20);//坦克中间的盖子
                g.drawLine(x+20,y+30,x+20,y+60);//坦克中间的炮筒
                g.setColor(Color.cyan);
                break;
            case 3:  //1表示坦克向左
                g.fill3DRect(x,y,60,10,false);//画出坦克左边的轮子
                g.fill3DRect(x,y+30,60,10,false);//画出坦克右边边的轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克中间的矩形
                g.fillOval(x+20,y+10,20,20);//坦克中间的盖子
                g.drawLine(x+30,y+20,x,y+20);//坦克中间的炮筒
                g.setColor(Color.cyan);
                break;

        }

    }
    //定义一个方法使得子弹集合的子弹都能击中坦克
    public void HitEnemyTank(){

        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);

        //编写我方子弹击中敌人坦克
        if (shot!= null && shot.isLive()){ //当我的子弹还存活
            //遍历敌人的坦克
            for (int i = 0; i < eHeroes.size(); i++) {
                EnemyHero enemyHero = eHeroes.get(i);
                HitTank(hero.shot, enemyHero);
            }

        }
    }
    }


    /**
     *
     * @param s
     * @Param eHero
     */
    //这里的击中 应该是父类的Tank 我一开始填写了敌人EnemyTank
    //导致  我方调用击中方法无法成功击中敌方坦克
    public void HitTank(Shot s, Tank eHero){
        //判断s击中坦克

        // 编写方法，判断我方子弹是否击中敌人坦克
        //根据方向改变子弹的x、y坐标
        switch (eHero.getDirection()) {
            case 0:   //up
            case 2:   //down
               if (s.getX() >eHero.getX() && s.getX() < eHero.getX()+40
               &&s.getY() > eHero.getY() && s.getY()< eHero.getY()+60){
                   s.isLive = false;
                   eHero.isLive = false;
                   //当我方子弹击中敌方坦克就从Vector集合去掉
                   eHeroes.remove(eHero);
                   //当我方击落一辆敌方坦克，就要记录下来
                   //需要判断被击落的是敌方坦克
                   if (eHero instanceof EnemyHero){
                       Recorder.addAllEnemyTankNum();
                   }
                   //创建一个Bomb对象加入到bombs集合
                   Bomb bomb = new Bomb(eHero.getX(), eHero.getY());
                   bombs.add(bomb);
               }
                break;
            case 1:   //right
            case 3:   //left
                if (s.getX() > eHero.getX() && s.getX() < eHero.getX()+60
                   &&s.getY() > eHero.getY() && s.getY()< eHero.getY()+40){
                    s.isLive = false;
                    eHero.isLive = false;
                    //当我方子弹击中敌方坦克就从Vector集合去掉
                    eHeroes.remove(eHero);
                    //当我方击落一辆敌方坦克，就要记录下来
                    //需要判断被击落的是敌方坦克
                    if (eHero instanceof EnemyHero){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建一个Bomb对象加入到bombs集合
                    Bomb bomb = new Bomb(eHero.getX(), eHero.getY());
                    bombs.add(bomb);
                }
                break;
        }


    }
    //编写方法，判断我们的坦克被敌人的坦克击中
    public void HitMyTank(){
        //首先，遍历敌人的坦克
        for (int i = 0; i < eHeroes.size(); i++) {
            //取出敌人的坦克
            EnemyHero enemyHero = eHeroes.get(i);
            //遍历enemyHero的所有子弹
            for (int j = 0; j < enemyHero.shots.size(); j++) {
                //取出所有子弹
                Shot shot = enemyHero.shots.get(j);
                //判断shot是否击中我的坦克
                if (hero.isLive && shot.isLive){
                    HitTank(shot, hero);
                }
            }
        }

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wasd键按下事件
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {  //w键向上
            //改变坦克的方向
            hero.setDirection(0);
            //坦克移动
            if (hero.getY() > 0 ){
                hero.moveUp();
            }

        }else if (e.getKeyCode() == KeyEvent.VK_D) {  //d键向右
            //改变坦克的方向
            hero.setDirection(1);
            //坦克移动
            if (hero.getX() + 60 < 1000){
                hero.moveRight();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_S) {  //s键向下
            //改变坦克的方向
            hero.setDirection(2);
            //坦克移动
            if (hero.getY() + 60 < 745){
                hero.moveDown();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_A) {  //a键向左
            //改变坦克的方向
            hero.setDirection(3);
            //坦克移动
            if (hero.getX() > 0){
                hero.moveLeft();
            }
        }
        //按下的是j键
        if (e.getKeyCode() == KeyEvent.VK_J){

            /*if (hero.shot ==null || !hero.shot.isLive){
                hero.shotEnemyTank(); //发射的是一颗子弹
            }*/
            hero.shotEnemyTank();

        }
        //让面板重绘
        this.repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //每隔100ms，重绘这个画板
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            //编写我方子弹击中敌人坦克
//            if (hero.shot!= null && hero.shot.isLive()){ //当我的子弹还存活
//                //遍历敌人的坦克
//                for (int i = 0; i < eHeroes.size(); i++) {
//                    EnemyHero enemyHero = eHeroes.get(i);
//                    HitTank(hero.shot, enemyHero);
//                }
//
//            }
            //判断己方是否击中敌方坦克
            HitEnemyTank();
            this.repaint();

            //判断敌人是否击中我方坦克
            HitMyTank();
            this.repaint();
        }

    }
}
