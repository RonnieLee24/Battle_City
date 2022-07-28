package method;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/3 - 07 - 03 - 11:53
 * @Description: method
 * @version: 1.0
 */
public class Homework01 {
    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        T1 t1 = new T1();
        t1.start();

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println("hi的次数：" + (++count));
            if (count == 5) {
                System.out.println("主线程（小弟）让 子线程（老大） 先吃");
                t1.join();  //  这里相当于让 t2 线程先执行完毕
                System.out.println("老大吃完了，小弟你接着吃吧...");
            }
        }
    }
}

class T1 extends Thread {
    private int count1 = 0;
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello次数 :" + (++count1));
        }
    }
}
