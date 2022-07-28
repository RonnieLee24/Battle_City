package method;

import java.util.SortedMap;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/2 - 07 - 02 - 16:08
 * @Description: method
 * @version: 1.0
 */
public class ThreadMethod01 {
    public static void main(String[] args) throws InterruptedException {

        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        threadDemo1.setName("LQ");
        threadDemo1.setPriority(Thread.MIN_PRIORITY);
        threadDemo1.start();    //  启动子线程

        //  主线程打印 5个 hi，然后我就中断 子线程的休眠
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("hi " + i);
        }

        System.out.println(threadDemo1.getName() + "  线程的优先级：" + threadDemo1.getPriority());

        threadDemo1.interrupt();    //  当执行到这里，就会中断 ThreadDemo1 线程的休眠


    }
}

class ThreadDemo1 extends Thread {  //  自定义的线程类
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                //  Thread.currentThread().getName() 获取当前线程的名称
                System.out.println(Thread.currentThread().getName() + "  吃包子~~~" + i);
            }

            System.out.println(Thread.currentThread().getName() + " 休眠中~~~");
            try {
                Thread.sleep(20000); //  休息5s
            } catch (InterruptedException e) {
                //  当该线程执行到一个 interrupt 方法时，就会 catch 一个 异常时，可以加入自己的业务代
                System.out.println(Thread.currentThread().getName() + "被 interrupt了");
            }
        }
    }
}