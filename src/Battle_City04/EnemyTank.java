package Battle_City04;

import java.util.Random;
import java.util.Set;
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
    //  增加成员，EnemyTank 可以得到敌人坦克的 Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //  分析
    //  1）Vector<EnemyTank> 在 MyPanel

    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //  这里提供一个方法，可以将 MyPanel 的成员 Vector<EnemyTank> enemyTanks = new Vector<>();
    //  设置到 EnemyTank类 的成员 enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //  编写方法，判断当前的这个敌人坦克，是否和 enemyTanks中的其它坦克发生了重叠或者碰撞
    public boolean isTouchEnemyTank() {
        //  判断敌人坦克（this）方向
        switch (this.getDirection()){
            case 0: //  上
                //  让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //  从Vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){ //  取出的坦克不能是自己
                        //  如果敌人坦克方向是 上/下
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 40】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 60】
                        //  当前坦克
                        //  左上角 坐标 【this.getX()，this.getY()】
                        //  右上角 坐标 【this.getX() + 40 ，this.getY()】

                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //  左上角 x 坐标 【this.getX()，this.getY()】
                            if (this.getX() >= enemyTank.getX()
                            && this.getX() <= enemyTank.getX() + 40
                            && this.getY() >= enemyTank.getY()
                            && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            //  右上角 坐标 【this.getX() + 40 ，this.getY()】
                            if (this.getX() + 40 >= enemyTank.getX()
                                && this.getX() + 40 <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY()
                                && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //  如果敌人坦克方向是 左/右
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 60】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 40】
                        //  当前坦克
                        //  左上角 坐标 【this.getX()，this.getY()】
                        //  右上角 坐标 【this.getX() + 40 ，this.getY()】
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //  左上角 x 坐标 【this.getX()，this.getY()】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                            //  右上角 坐标 【this.getX() + 40 ，this.getY()】
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }

                break;
            case 1: //  右
                //  让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //  从Vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){ //  取出的坦克不能是自己
                        //  如果敌人坦克方向是 上/下
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 40】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 60】
                        //  当前坦克
                        //  右上角 坐标 【this.getX() + 60，this.getY()】
                        //  右下角 坐标 【this.getX() + 60, this.getY() + 40】

                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //  右上角 x 坐标 【this.getX() + 60，this.getY()】
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                            //  右下角 坐标 【this.getX() + 60 ，this.getY() + 40】
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //  如果敌人坦克方向是 左/右
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 60】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 40】
                        //  当前坦克
                        //  右上角 坐标 【this.getX() + 60, this.getY()】
                        //  右下角 坐标 【this.getX() + 60 ，this.getY() + 40】
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //  右上角 坐标 【this.getX() + 60，this.getY()】
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                            //  右下角 坐标 【this.getX() + 60 ，this.getY() + 40】
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }






                break;
            case 2: //  下

                //  让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //  从Vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){ //  取出的坦克不能是自己
                        //  如果敌人坦克方向是 上/下
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 40】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 60】
                        //  当前坦克
                        //  左下角 坐标 【this.getX()，this.getY() + 60】
                        //  右下角 坐标 【this.getX() + 40, this.getY() + 60】

                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //  左下角 x 坐标 【this.getX()，this.getY() + 60】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }
                            //  右下角 坐标 【this.getX() + 60 ，this.getY() + 40】
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //  如果敌人坦克方向是 左/右
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 60】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 40】
                        //  当前坦克
                        //  左下角 坐标 【this.getX(), this.getY() + 60】
                        //  右下角 坐标 【this.getX() + 40 ，this.getY() + 60】
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //  左下角 坐标 【this.getX() + 60，this.getY()】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }
                            //  右下角 坐标 【this.getX() + 40 ，this.getY() + 40】
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60<= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }


                break;
            case 3: //  左
                //  让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //  从Vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){ //  取出的坦克不能是自己
                        //  如果敌人坦克方向是 上/下
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 40】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 60】
                        //  当前坦克
                        //  左上角 坐标 【this.getX()，this.getY()】
                        //  右下角 坐标 【this.getX(), this.getY() + 40】

                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //  右上角 x 坐标 【this.getX()，this.getY()】
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                            //  右下角 坐标 【this.getX()，this.getY() + 40】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //  如果敌人坦克方向是 左/右
                        //  敌人坦克
                        //  x 的范围是： 【enemyTank.getX()，enemyTank.getX() + 60】
                        //  y 的范围是： 【enemyTank.getY()，enemyTank.getY() + 40】
                        //  当前坦克
                        //  左上角 坐标 【this.getX(), this.getY()】
                        //  左下角 坐标 【this.getX()，this.getY() + 40】
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //  左上角 坐标 【this.getX()，this.getY()】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                            //  左下角 坐标 【this.getX()，this.getY() + 40】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }

        return false;   //  如果没有进入到 switch 中，相当于没有碰撞，返回 false
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
                        if (getY() > 0 && !isTouchEnemyTank()) {
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
                        if ((getX() + 60) < 1000 && !isTouchEnemyTank()) {
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
                        if ((getY() + 60) < 750 && !isTouchEnemyTank()) {
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
                        if (getX() > 0 && !isTouchEnemyTank()) {
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
