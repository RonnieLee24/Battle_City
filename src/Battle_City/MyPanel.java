package Battle_City;

import javax.crypto.interfaces.PBEKey;
import javax.swing.*;
import java.awt.*;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:40
 * @Description: Battle_City
 * @version: 1.0
 * 坦克大战绘图区域
 */
public class MyPanel extends JPanel {

    //  定义我的坦克
    My_tank my_tank = null;

    public MyPanel(){
        my_tank = new My_tank(100, 100);    //  初始化自己的坦克
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);    //  填充矩形，默认黑色

        //  画出坦克 -- 封装方法
        drawTank(my_tank.getX(), my_tank.getY(), g, 0, 0);
        drawTank(my_tank.getX() + 60, my_tank.getY(), g, 0, 1);

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
            case 0: //  我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //  敌人的坦克
                g.setColor(Color.yellow);
                break;
        }

        //  根据坦克方向，来绘制坦克
        switch (direction){
            case 0: //  表示向上
                g.fill3DRect(x, y, 10, 60, false);  //  画出坦克左边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);  //  画出坦克中间的矩形
                g.fill3DRect(x + 30, y, 10, 60, false);  //  画出坦克右边的轮子
                g.fillOval(x + 10, y + 20, 20, 20);  //  画出坦克中间的圆形锅盖
                g.drawLine(x + 20, y + 30, x + 20, y);  //  画出坦克的炮筒
                break;
        }

    }


}
