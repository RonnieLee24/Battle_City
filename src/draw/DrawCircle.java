package draw;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/23 - 05 - 23 - 23:39
 * @Description: draw
 * @version: 1.0
 * 演示如何在面板上画出圆形
 */
public class DrawCircle extends JFrame{ //  JFrame 相当于画框（窗口），面板要嵌入到画框中 ---> 画框.add(画板)

    //  定义一个面板
    private MyPanel myPanel = null;

    public static void main(String[] args) {
        new DrawCircle();
    }

    //  设置一个构造器
    public DrawCircle() {
        //  初始化面板
        myPanel = new MyPanel();
        //  现在将面板放入到画框（窗口）里面去
        this.add(myPanel);
        //  设置窗口的大小
        this.setSize(400, 300);
        //  当点击窗口的小 ×，程序就完全退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  //  可以显示

    }
}

//  1. 先定义一个 MyPanel，继承JPanel 类， 画图形，就在面板上画
class MyPanel extends JPanel {


    //  说明：
    //  1. MyPanel 对象就是一个画板
    //  2. Graphics g 把 g 理解成一支画笔
    //  3. Graphics 提供了喝多绘图的方法

    @Override
    public void paint(Graphics g) { //  绘图方法
        super.paint(g); // 调用父类方法，完成初始化
        System.out.println("paint 方法被调用了！！！");
        //  画出一个圆形
        g.drawOval(10, 10, 100, 100);

        //  演示绘制不同的图形...

        //  nbsp 在 html中是空格的意思

        //  画直线 drawLine(int x1, int y1, int x2, int y2)
        g.drawLine(10, 10, 100, 100);

        //  画矩形边框 drawRect(intNBSPx, intNBSPy, intNBSPwidth, intNBSPheight)
        g.drawRect(10, 10, 100, 100);

        //  画填充矩形 fillRect(intNBSPx, intNBSPy, intNBSPwidth, intNBSPheight)
        //  可以有颜色
        //  设置画笔的颜色
        g.setColor(Color.pink);
        g.fillRect(10,  10, 100, 100);

        //  填充椭圆  fillOval(intNBSPx, intNBSPy, intNBSPwidth, intNBSPheight)
        g.setColor(Color.red);
        g.fillOval(10, 10, 100, 100);

        //  画图片 drawImage(ImageNBSPimg, intNBSPx, intNBSPy,...)
        //  1）获取图片资源，/wind.png 表示在该项目的根目录去获取 wind.png 图片资源
        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/wind.png"));
        g.drawImage(image, 10, 10, 1143, 819, this);

        //  画字符串 drawString(StringNBSPstr, intNBSPx, intNBSPy)  ---> 写字
        //  给画笔设置颜色和字体
        g.setColor(Color.red);
        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("北京欢迎你", 100, 100);    //  （x, y）字体的左下角
    }
}