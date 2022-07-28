package Battle_City02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:40
 * @Description: Battle_City
 * @version: 1.0
 * 坦克大战绘图区域
 */

//  为了监听 键盘事件，实现KeyListener接口
public class MyPanel extends JPanel implements KeyListener {

    //  定义我的坦克
    My_tank my_tank = null;

    //  定义敌人坦克，放入到 Vector中
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public MyPanel(){
        my_tank = new My_tank(100, 100);    //  初始化自己的坦克
        my_tank.setSpeed(7);

        int enemyTankSize = 3;
        //  初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirection(2);
            enemyTanks.add(enemyTank);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);    //  填充矩形，默认黑色

        //  画出坦克 -- 封装方法
        drawTank(my_tank.getX(), my_tank.getY(), g, my_tank.getDirection(), 1);

        //  画出敌人的坦克，遍历 Vector
        for (int i = 0; i < enemyTanks.size(); i++) {   //  因为坦克有可能被销毁的，所以要用 enemyTanks.size()
            //  取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);


        }
    }

    /**
     *
     * @param x 坦克的左上角 x 坐标
     * @param y 坦克的左上角 y 坐标
     * @param g 画笔
     * @param direction 坦克方向（上下左右）
     * @param type  坦克类型
     */
    //  编写方法，画出坦克
    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        //  根据不同类型坦克，设置不同颜色
        switch (type){
            case 0: //  敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //  我的坦克
                g.setColor(Color.yellow);
                break;
        }

        //  根据坦克方向，来绘制坦克
        //  direct 表示方向（0, 1, 2, 3）
        switch (direction){
            case 0: //  表示向上
                g.fill3DRect(x, y, 10, 60, false);  //  画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);  //  画出坦克中间的矩形
                g.fill3DRect(x + 30, y, 10, 60, false);  //  画出坦克右边的轮子
                g.fillOval(x + 10, y + 20, 20, 20);  //  画出坦克中间的圆形锅盖
                g.drawLine(x + 20, y + 30, x + 20, y);  //  画出坦克的炮筒
                break;
            case 1: //  表示向右
                g.fill3DRect(x, y, 60, 10, false);  //  画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);  //  画出坦克中间的矩形
                g.fill3DRect(x, y + 30, 60, 10, false);  //  画出坦克右边的轮子
                g.fillOval(x + 20, y + 10, 20, 20);  //  画出坦克中间的圆形锅盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);  //  画出坦克的炮筒
                break;
            case 2: //  表示向下
                g.fill3DRect(x, y, 10, 60, false);  //  画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);  //  画出坦克中间的矩形
                g.fill3DRect(x + 30, y, 10, 60, false);  //  画出坦克右边的轮子
                g.fillOval(x + 10, y + 20, 20, 20);  //  画出坦克中间的圆形锅盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);  //  画出坦克的炮筒
                break;
            case 3: //  表示向左
                g.fill3DRect(x, y, 60, 10, false);  //  画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);  //  画出坦克中间的矩形
                g.fill3DRect(x, y + 30, 60, 10, false);  //  画出坦克右边的轮子
                g.fillOval(x + 20, y + 10, 20, 20);  //  画出坦克中间的圆形锅盖
                g.drawLine(x + 30, y + 20, x, y + 20);  //  画出坦克的炮筒
                break;

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    //  处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){   //  按下 W 键
            //  改变坦克的方向
            my_tank.setDirection(0);
            //  修改坦克的坐标 y -= 1
            my_tank.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_D){  //  按下 D 键
            my_tank.setDirection(1);
            my_tank.moveRight();
        }else if (e.getKeyCode() == KeyEvent.VK_S){ //  按下 S 键
            my_tank.setDirection(2);
            my_tank.moveDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            my_tank.setDirection(3);
            my_tank.moveLeft();
        }

        //  让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
