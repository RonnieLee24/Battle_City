package Battle_City04;

import javax.swing.*;
import javax.swing.plaf.multi.MultiButtonUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Reader;
import java.rmi.Remote;
import java.util.Vector;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:40
 * @Description: Battle_City
 * @version: 1.0
 * 坦克大战绘图区域
 */

//  为了监听 键盘事件，实现KeyListener接口
//  为了让 Panel 不停地 重绘子弹，需要将 MyPanel 实现 Runnable，当做一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {

    //  定义我的坦克
    My_tank my_tank = null;

    //  定义敌人坦克，放入到 Vector中
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //  定义一个存放 Node 对象的 Vector，用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();

    //  定义一个Vector，用于存放炸弹 [泛型 和 集合的使用]
    Vector<Bomb> bombs = new Vector<>();
    //  当子弹击中坦克时，加入一个 Bomb 对象到 bombs

    //  定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        //  在 MyPanel 中，游戏一启动，我们就进行数据的恢复
        if (key.equals("2")) {
            nodes = Recorder.getNodesAndEnemyTankNumRec();
        }

        my_tank = new My_tank(100, 100);    //  初始化自己的坦克
        my_tank.setSpeed(7);

        int enemyTankSize = 3;

        switch (key) {
            case "1":
                //  初始化敌人的坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    //  设置方向
                    enemyTank.setDirection(2);
                    //  设置速度
                    enemyTank.setSpeed(1);
                    //  启动敌人坦克线程，让它动起来
                    new Thread(enemyTank).start();  //  执行 run（） 方法：1）自由移动 2）被击中后消失（myTank调用 shotEnemyTank()方法创建
                    //  相应子弹对象，
                    //  然后在 MyPanel线程中调用 hitTank() 方法判断 如果敌方坦克被击中的话，aLive == false[Over]
                    //  MyPanel() 线程的第二个作用：this.repaint() 一直重绘，保持图像的更新
                    //  MyPanel线程在 TankGame04 中启动


                    //  给该  enemyTank 加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    //  加入 enemyTank的 Vector 成员
                    enemyTank.shots.add(shot);
                    //  启动 shot 对象
                    new Thread(shot).start();   //  执行 run() 方法
                    //  1）子弹移动 2）触碰边界和射到敌军坦克时，结束线程

                    enemyTank.setEnemyTanks(enemyTanks); // *** 将 enemyTanks 设置给 enemyTank *** //

                    Recorder.setEnemyTanks(enemyTanks);  // *** 将 enemyTanks 设置给 Recorder 的 enemyTank *** //
                    //  加入
                    enemyTanks.add(enemyTank);
                }

                break;
            case "2": //  继续上局游戏

                //  初始化敌人的坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //  设置方向
                    enemyTank.setDirection(node.getDirect());
                    //  设置速度
                    enemyTank.setSpeed(1);
                    //  启动敌人坦克线程，让它动起来
                    new Thread(enemyTank).start();  //  执行 run（） 方法：1）自由移动 2）被击中后消失（myTank调用 shotEnemyTank()方法创建
                    //  相应子弹对象，
                    //  然后在 MyPanel线程中调用 hitTank() 方法判断 如果敌方坦克被击中的话，aLive == false[Over]
                    //  MyPanel() 线程的第二个作用：this.repaint() 一直重绘，保持图像的更新
                    //  MyPanel线程在 TankGame04 中启动


                    //  给该  enemyTank 加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    //  加入 enemyTank的 Vector 成员
                    enemyTank.shots.add(shot);
                    //  启动 shot 对象
                    new Thread(shot).start();   //  执行 run() 方法
                    //  1）子弹移动 2）触碰边界和射到敌军坦克时，结束线程

                    enemyTank.setEnemyTanks(enemyTanks); // *** 将 enemyTanks 设置给 enemyTank *** //

                    Recorder.setEnemyTanks(enemyTanks);  // *** 将 enemyTanks 设置给 Recorder 的 enemyTank *** //
                    //  加入
                    enemyTanks.add(enemyTank);
                }
                break;

            default:
                System.out.println("你的输入有误，请输入1或者2");
        }

        //  初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
    }

    //  编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g) {  //  因为要画图，所以我们需要画笔 g 这个参数

        //  画出玩家的总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);    //  画出一个敌方坦克（注意: drawTank方法将画笔置为了蓝色）
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum() + "" , 1080, 100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);    //  填充矩形，默认黑色
        showInfo(g);

        //  画出坦克 -- 封装方法（止于瓯当坦克存活的时候，才会画出坦克）
        if (my_tank.isLive) {
            drawTank(my_tank.getX(), my_tank.getY(), g, my_tank.getDirection(), 1);
        }

        //  画出 my_tank 射击的子弹 (多颗) --- 子弹集合
        if (my_tank.shot != null && my_tank.shot.isLive == true) {
            System.out.println("子弹被绘制");
            g.draw3DRect(my_tank.shot.x, my_tank.shot.y, 2, 2, false);
        }

        //  如果 bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //  取出炸弹
            Bomb bomb = bombs.get(i);
            //  根据当前这个 bomb对象的 life 值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            }
            if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //  让这个炸弹的生命值减少
            bomb.lifeDown();
            //  如果 bomb 的 life 为 0，就从 bombs 集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //  画出敌人的坦克，遍历 Vector [并在敌人坦克存活的时候，画出敌人的子弹]
        for (int i = 0; i < enemyTanks.size(); i++) {   //  因为坦克有可能被销毁的，所以要用 enemyTanks.size()
            //  从Vector 取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //  判断当前敌人坦克是否还存活
            if (enemyTanks.get(i).isLive == true) { //  只有当敌人的坦克是活的，才去画图！！！
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                //  画出enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //  取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //  绘制
                    if (shot.isLive == true) {  // isLive == true
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        //  从 Vector 移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }

        }
    }

    /**
     * @param x         坦克的左上角 x 坐标
     * @param y         坦克的左上角 y 坐标
     * @param g         画笔
     * @param direction 坦克方向（上下左右）
     * @param type      坦克类型
     */
    //  编写方法，画出坦克
    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        //  根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0: //  敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //  我的坦克
                g.setColor(Color.yellow);
                break;
        }

        //  根据坦克方向，来绘制坦克
        //  direct 表示方向（0, 1, 2, 3）
        switch (direction) {
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

    //  编写方法，判断我方的子弹是否击中敌人坦克
    public void hitTank(Shot s, EnemyTank enemyTank) {
        //  判断 s 击中坦克
        switch (enemyTank.getDirection()) {
            case 0: // 坦克 方向为 上和下 时，坦克的区域是一样的，只是炮筒的方向不一样
            case 2:
                if ((s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                        && s.y >= enemyTank.getY() && s.y <= enemyTank.getY() + 60)) {
                    s.isLive = false;    //  其中后，子弹就失去意义了
                    enemyTank.isLive = false;   //  同样，敌军坦克也会挂掉
                    //  创建 Bomb 对象，并加入到 bombs 集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);

                    //  当我方击毁一个敌人坦克时，就对数据 allEnemyTankNum++
                    Recorder.addAllEnemyTankNum();


                }
                break;
            case 1: // 坦克 右
            case 4: // 坦克 左
                if ((s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                        && s.y >= enemyTank.getY() && s.y <= enemyTank.getY() + 40)) {
                    s.isLive = false;    //  击中后，子弹就失去意义了
                    enemyTank.isLive = false;

                    //  创建 Bomb 对象，并加入到 bombs 集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);

                    Recorder.addAllEnemyTankNum();
                }
                break;
        }
    }

    //  编写方法，判断敌军的子弹是否击中我方坦克
    public void hitMyHeart(Shot s, My_tank my_tank) {
        //  判断 s 击中坦克
        switch (my_tank.getDirection()) {
            case 0: // 坦克 方向为 上和下 时，坦克的区域是一样的，只是炮筒的方向不一样
            case 2:
                if ((s.x > my_tank.getX() && s.x < my_tank.getX() + 40
                        && s.y >= my_tank.getY() && s.y <= my_tank.getY() + 60)) {
                    s.isLive = false;    //  其中后，子弹就失去意义了
                    my_tank.isLive = false;   //  同样，敌军坦克也会挂掉
                    //  创建 Bomb 对象，并加入到 bombs 集合
                    Bomb bomb = new Bomb(my_tank.getX(), my_tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: // 坦克 右
            case 4: // 坦克 左
                if ((s.x > my_tank.getX() && s.x < my_tank.getX() + 60
                        && s.y >= my_tank.getY() && s.y <= my_tank.getY() + 40)) {
                    s.isLive = false;    //  击中后，子弹就失去意义了
                    my_tank.isLive = false;
                    //  创建 Bomb 对象，并加入到 bombs 集合
                    Bomb bomb = new Bomb(my_tank.getX(), my_tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    //  处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {   //  按下 W 键
            //  改变坦克的方向
            my_tank.setDirection(0);
            //  修改坦克的坐标 y -= 1
            if (my_tank.getY() > 0) {
                my_tank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {  //  按下 D 键
            my_tank.setDirection(1);
            if ((my_tank.getX() + 60) < 1000) {
                my_tank.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) { //  按下 S 键
            my_tank.setDirection(2);
            if ((my_tank.getY() + 60) < 750) {
                my_tank.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            my_tank.setDirection(3);
            if (my_tank.getX() > 0) {
                my_tank.moveLeft();
            }
        }

        //  如果用户按下的是 J，就发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //  子弹的初始化 || 判断 my_tank的子弹是否被销毁（被销毁了才能发射新的的子弹）
            if (my_tank.shot == null || !(my_tank.shot.isLive)) {
                my_tank.shotEnemyTank();
            }  // 单个子弹的情形

//            //  现在写的是连发的情景
//            if (my_tank.shots.size() < 5) {
//                my_tank.shotEnemyTank();
//            }
        }

        //  让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { //  每隔100 ms，刷新绘图区域，子弹就移动起来了
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //  判断是否击中了敌人
            if (my_tank.shot != null && my_tank.shot.isLive == true) {  //  当我的子弹还存活
                //  遍历敌人所有的坦克
                for (int j = 0; j < enemyTanks.size(); j++) {
                    hitTank(my_tank.shot, enemyTanks.get(j));
                }
            }

            //  判断自己是否被击中
            for (int i = 0; i < enemyTanks.size(); i++) {   //  因为坦克有可能被销毁的，所以要用 enemyTanks.size()
                //  从Vector 取出坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                //  判断当前敌人坦克是否还存活
                if (enemyTank.isLive && my_tank.isLive) { //  只有当敌人的坦克是活的，才去画图！！！
                    //  画出enemyTank 所有子弹
                    for (int j = 0; j < enemyTank.shots.size(); j++) {
                        //  取出子弹
                        Shot shot = enemyTank.shots.get(j);
                        //  绘制
                        hitMyHeart(shot, my_tank);

                    }
                }

            }

            this.repaint();
        }
    }
}


