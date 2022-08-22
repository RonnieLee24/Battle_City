package Battle_City04;

import javax.xml.crypto.NodeSetData;
import java.awt.*;
import java.io.*;
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

    //  定义输入流
    private static BufferedReader br = null;

    private static String recordFile = "e:/myRecord1.txt";
    //  定义 Vector，指向 MyPanel 对象的 敌人坦克 Vector
    private static Vector<EnemyTank> enemyTanks = null;

    //  定义一个 Node 的 Vector，用于存储保存敌人的信息 node
    private static Vector<Node> nodes= new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //  增加一个方法，用于读取 recordFile，恢复相关信息
    //  该方法，在继续上局游戏的时候调用即可
    public static Vector<Node> getNodesAndEnemyTankNumRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //  循环读取文件，生成 nodes 集合
            String line = "";   //  每一行的数据都是 255 40 0
            while ((line = br.readLine()) != null) {
                String[] x_y_d = line.split(" ");   //  利用空格分割
                Node node = new Node((Integer.parseInt(x_y_d[0])) ,
                        Integer.parseInt(x_y_d[1]),
                        Integer.parseInt(x_y_d[2])); // String ---> Int

                //  后面为了方便管理，我们把这些 Node 放到 Vector 中
                nodes.add(node);    //  放入到 nodes Vector
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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



    //  增加一个方法，当游戏退出时，我们将 allEnemyTankNum 保存到 readFile
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "");
            bw.newLine();
            //  遍历敌人坦克的 Vector，然后根据情况保存即可
            //  OOP，定义一个属性，然后通过setXxx 方法得到 敌人坦克的Vector
            if (enemyTanks != null) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank.isLive) { //  建议判断
                        //  保存该 enemyTank 信息
                        String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                        //  写入到文件
                        bw.write(record + "\r\n");

                    }
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
