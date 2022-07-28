package Battle_City04;

import javax.swing.*;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:44
 * @Description: Battle_City
 * @version: 1.0
 */

public class TankGame04 extends JFrame {
    //  定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame04 tankGame02 = new TankGame04();
    }
    public TankGame04(){
        mp = new MyPanel();
        //  将 mp 放入到 Thread，并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);   //  把面板（就是游戏的绘图区域）
        this.setSize(1200, 950);
        this.addKeyListener(mp);    //  让 JFrame 监听 mp 的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
