package Battle_City;

import javax.swing.*;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:44
 * @Description: Battle_City
 * @version: 1.0
 */

public class TankGame01 extends JFrame {
    //  定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);   //  把面板（就是游戏的绘图区域）
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
