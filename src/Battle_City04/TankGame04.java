package Battle_City04;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:44
 * @Description: Battle_City
 * @version: 1.0
 */

public class TankGame04 extends JFrame {
    //  定义MyPanel
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame04 tankGame02 = new TankGame04();
    }


    public TankGame04(){
        System.out.println("请输入选择 1：新游戏 2：继续上局");
        String key = scanner.next();

        mp = new MyPanel(key);
        //  将 mp 放入到 Thread，并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);   //  把面板（就是游戏的绘图区域）

        this.setSize(1300, 950);
        this.addKeyListener(mp);    //  让 JFrame 监听 mp 的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //  在 JFrame 中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.out.println("7777");
                System.exit(0); //  正常退出
            }
        });
    }
}
