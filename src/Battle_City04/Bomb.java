package Battle_City04;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/25 - 07 - 25 - 10:28
 * @Description: Battle_City04
 * @version: 1.0
 */
public class Bomb {
    int x, y;   //  炸弹坐标
    int life = 9;   //  炸弹的生命周期
    boolean isLive = true;  //  是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {    //  配合图片的爆炸效果
        if (life > 0) {
            life--;
        }else {
            isLive = false;
        }
    }
}
