package com.company.TankGame2_2;

import java.io.*;
import java.util.Vector;

/**
 * @author 梁东明
 * 在Editor 的File and Code Templates 修改
 * 记录坦克相关的信息，和文件交互
 */
public class Recorder  {
    //定义变量，记录我方坦克击落的坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象，准备把数据写入文件
    private static BufferedWriter bw = null;
    //定义IO对象，准备文件数据读入内存
    private static BufferedReader br = null;
    //定义文件的路径
    private static String recordFile = "D:\\IOTest\\myRecord.txt";
    //定义一个Node的Vector对象用于保存敌人信息
    private static Vector<Node> nodes = new Vector<>();

    //定义Vector，指向TankPenal对象的 敌人坦克Vector
    private static Vector<EnemyHero> enemyHeroes = null;

    public static void setEnemyHeroes(Vector<EnemyHero> enemyHeroes) {
        Recorder.enemyHeroes = enemyHeroes;
    }
    //增加一个方法用于读取文件，恢复相关方法
    //该方法在继续上局时使用
    public static Vector<Node> getNodesAndAllEnemyTankRec(){
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum =Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line = " ";
            while ((line = br.readLine()) != null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1])
                        , Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }



    //增加一个方法，当游戏退出时，将allEnemyTankNum保存到recordFile
    //记录敌方坦克的坐标
    public static void keepRecord()  {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");
            //遍历敌人坦克的Vector。然后根据情况保存
            //oop,定义一个属性，然后通过setXxx（）方法得到敌人坦克的Vector
            for (int i = 0; i < enemyHeroes.size(); i++) {
                //取出敌人坦克
                EnemyHero enemyHero = enemyHeroes.get(i);
                if (enemyHero.isLive){
                    //保存该EnemyHero的信息
                    String recordTank = enemyHero.getX() + " "+enemyHero.getY() + " "+enemyHero.getDirection();
                    bw.write(recordTank + "\r\n");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方记录敌方坦克数量就应该 allEnemyTank ++
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
