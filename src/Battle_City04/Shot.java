package Battle_City04;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/23 - 07 - 23 - 11:27
 * @Description: Battle_City3
 * @version: 1.0
 * 射击子弹
 */
public class Shot implements Runnable {
    int x; //  子弹 x 坐标
    int y; //  子弹 y 坐标
    int direct  = 0; // 子弹方向
    int speed = 2;  //  子弹的速度
    boolean isLive = true;  //  子弹是否还存活

    //  构造器
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() { //  射击
        while (true){
            //  休眠 50 ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  根据方向来改变 x, y 坐标
            switch (direct){
                case 0://   上
                    y -= speed;
                    break;
                case 1://   右
                    x += speed;
                    break;
                case 2://   下
                    y += speed;
                    break;
                case 3://   左
                    x -= speed;
                    break;
            }
            //  测试：这里我们输出 x，y 的坐标
            System.out.println("x的位置：" + x + " y的位置：" + y);
            //  当子弹移动到前面面板的边界时，就应该销毁（把启动的子弹的线程销毁）
            //  当子弹碰到 敌人坦克时，也应该销毁（结束线程）
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
