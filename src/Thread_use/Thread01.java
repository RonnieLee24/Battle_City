package Thread_use;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/6/14 - 06 - 14 - 0:33
 * @Description: Thread_use
 * @version: 1.0
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {

        //  创建 Cat对象，可以当做线程使用
        new Cat().start();  //  启动线程

        //  说明：当main 线程启动一个子线程 Thread-0，主线程不会阻塞，会继续执行
        //  这时，主线程和子线程是交替执行。。。
        System.out.println("主线程继续执行" + Thread.currentThread().getName());   //  主线程的名称就叫 main
        for (int i = 0; i < 60; i++) {
            System.out.println("主线程 i=" + i);
            //  让主线程休眠
            Thread.sleep(1000);
        }

    }
}


//  老韩说明
//  1）当一个类继承了 Thread类，该类就可以当成线程使用
//  2）我们会重写 run() 方法，写上自己的业务代码
//  3）run Thread 类实现了 Runnable 接口的 run 方法
/*
@Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
 */

class Cat extends Thread {
    int times = 0;
    @Override
    public void run() { //  重写 run() 方法，写上自己的业务逻辑
        while (true){
            System.out.println("喵喵，我是小猫咪" + (++times) + "线程名称=" + Thread.currentThread().getName());
            //  让线程休眠1秒
            try {
                Thread.sleep(1000); //  单位是 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 80){
                break;  //  当 times 到80，退出 while 循环，这时线程也就退出...
            }
        }
    }
}