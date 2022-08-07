package Battle_City04;

import java.util.Random;
import java.util.Vector;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/6/9 - 06 - 09 - 0:00
 * @Description: Battle_City02
 * @version: 1.0
 */
public class EnemyTank extends Tank implements Runnable{
    //  在敌人坦克类，使用  Vector 保存多个 Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){
            //  这里我们判断如果 shot.size() = 0, 创建一颗子弹，放入到
            //  shots 集合，并启动
            if(isLive && shots.size() < 1){   //  先判断坦克是活着的，如果坦克已经没了的话，就没必要给它增加子弹了呦！！！
                //  判断坦克的方向
                //  创建 Shot 对象
                Shot s= null;
                switch (getDirection()) {    //  得到自己坦克的方向
                    case 0: //  向上
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1: //  向右
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2: //  向下
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3: //  向左
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                //  启动
                new Thread(s).start();
            }

            //  根据坦克的方向来继续移动
            //  然后随机地改变坦克方向
            switch (getDirection()){
                case 0: //  向上
                    //  让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();   //  调用父类方法
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: //  向右
                    for (int i = 0; i < 30; i++) {
                        if ((getX() + 60) < 1000) {
                            moveRight();   //  调用父类方法
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: //  向下
                    for (int i = 0; i < 30; i++) {
                        if ((getY() + 60) < 750) {
                            moveDown();   //  调用父类方法
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //  向左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();   //  调用父类方法
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //  移动完之后，让其休眠 50ms 【如果不休眠的话，那么就会像无头苍蝇（一开始自己做的那样）】
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //  然后随机地改变坦克的方向 0 - 3
            setDirection((int)(Math.random() * 4));

            //  听老韩说，写并发程序，一定要考虑清楚，该线程什么时候结束
            //  当坦克被子弹击中时候，enemyTank.aLive置为 false
            if (!isLive){
                break;  //  退出线程
            }
        }
    }
}
