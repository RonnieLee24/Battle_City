package Battle_City04;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/8/19 - 08 - 19 - 1:22
 * @Description: Battle_City04
 * @version: 1.0
 * 用于记录相关信息，和文件交互
 */
public class Recorder {
    //  定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    //  定义IO对象，准备写入到文件中
    private static BufferedWriter bw = null;
    private static String recordFile = "e:/myRecord1.txt";
    //  定义 Vector，指向 MyPanel 对象的 敌人坦克 Vector
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //  增加一个方法，当游戏退出时，我们将 allEnemyTankNum 保存到 readFile
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "");
            bw.newLine();
            //  遍历敌人坦克的 Vector，然后根据情况保存即可
            //  OOP，定义一个属性，然后通过setXxx 方法得到 敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { //  建议判断
                    //  保存该 enemyTank 信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    //  写入到文件
                    bw.write(record + "\r\n");

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

    //  当我方坦克击毁一个敌人坦克，就应当 allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }


}
