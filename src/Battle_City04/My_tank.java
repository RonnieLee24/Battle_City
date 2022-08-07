package Battle_City04;

import java.util.Vector;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/5/29 - 05 - 29 - 23:39
 * @Description: Battle_City
 * @version: 1.0
 */
public class My_tank extends Tank {
    //  定义一个 shot 对象，表示一个射击（线程）
    Shot shot = null;
//    Vector<Shot> shots = new Vector<>();    //  设置一个弹夹来存放子弹
    //  定义一个变量表示 我方坦克是否存活
    boolean isLive = true;

    public My_tank(int x, int y) {
        super(x, y);
    }

    //  射击
    public void shotEnemyTank(){
        //  创建 Shot 对象
        switch (getDirection()){    //  得到自己坦克的方向
            case 0: //  向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: //  向右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: //  向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: //  向左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }

        //  把新建的 shot 放入到集合 shots 集合中
//        shots.add(shot);

        //  启动我们的 Shot 线程
        new Thread(shot).start();
    }

}
