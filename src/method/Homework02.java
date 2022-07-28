package method;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/3 - 07 - 03 - 12:53
 * @Description: method
 * @version: 1.0
 */
public class Homework02 {
    public static void main(String[] args) throws InterruptedException {
        T2 t2 = new T2();
        Thread thread = new Thread(t2);
        int count2 = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("hi 次数=" + (++count2));
            if (count2 == 5){
                thread.start(); //  启动子线程，输出 hello...
                thread.join();  //  立即将 t2 子线程，插入到 main，让t2 先执行
                System.out.println("子线程执行完毕，主线程继续");
            }
        }
    }
}

class T2 implements Runnable{
    int count1 = 0;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello次数 " + (++count1));
        }
    }
}