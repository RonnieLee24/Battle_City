package Homework;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/10 - 07 - 10 - 16:37
 * @Description: Homework
 * @version: 1.0
 */
public class home2 {
    public static void main(String[] args) {
        T t = new T();
        Thread thread1 = new Thread(t);
        thread1.setName("t1");
        Thread thread2 = new Thread(t);
        thread2.setName("t2");
        thread1.start();
        thread2.start();
    }
}

//  编程取款的线程
//  1）因为这里涉及到多个线程共享资源，所以我们使用实现 Runnable 接口方式
//  2）每次取出 1000 块
class T implements Runnable{
    private int money = 100000;
    @Override
    public void run() {
        while (true) {
            //  解读
            //  1）这里使用了 synchronized 实现了线程同步
            //  2）当多个线程执行到这里时，就会去争夺 this 对象锁
            //  3）哪个线程（争夺到）获取到了 this 对象锁（t：必须是同一个对象），就执行 synchronized 代码块
            //  执行完后，会释放 this 对象锁
            //  4）争夺不到 this 对象锁，就 blocked，准备继续争夺，
            //  5）this 对象是非公平锁

            synchronized (this) {
                //  判断余额是否足够
                if (money < 1000) {
                    System.out.println("余额不足");
                    break;
                }
                money -= 1000;
                System.out.println(Thread.currentThread().getName() + "取出了1000块，当前余额=" + money);
                //  休眠1s
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
