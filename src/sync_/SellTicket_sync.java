package sync_;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/2 - 07 - 02 - 13:52
 * @Description: ticket
 * @version: 1.0
 * 使用多线程，模拟三个窗口同时售票 100 张
 */
public class SellTicket_sync {
    public static void main(String[] args) {
        //  模拟3个窗口，所以要创建3个这样的对象
        System.out.println("=====使用实现接口方式来售票====");

        SellTicket03 sellTicket03 = new SellTicket03();

        Thread thread1 = new Thread(sellTicket03);
        Thread thread2 = new Thread(sellTicket03);
        Thread thread3 = new Thread(sellTicket03);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

//  使用 Thread 方式

class SellTicket01 extends Thread {

    private static int TicketNum = 100; //  让多个线程共享 num

    @Override
    public void run() {
        while (true) {

        }
    }
}

class SellTicket02 implements Runnable {

    private static int TicketNum = 100; //  让多个线程共享 num


    @Override
    public void run() {
        while (true) {
            if (TicketNum <= 0) {
                System.out.println("售票结束......");
                break;
            }

            //   休眠 50ms，模拟
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                    + " 剩余票数=" + (--TicketNum));
        }
    }
}

//  实现接口方式，使用 synchronized 实现线程同步
class SellTicket03 implements Runnable {

    private int TicketNum = 100; //  让多个线程共享 num
    private boolean loop = true;    //  控制 run 方法的变量

    Object object = new Object();

    public void sell() {   //  同步方法，在同一时刻，只能有一个线程来执行 sell() 方法
        synchronized (object) {
            if (TicketNum <= 0) {
                System.out.println("售票结束......");
                loop = false;
                return;
            }
            //   休眠 50ms，模拟
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票"
                    + " 剩余票数=" + (--TicketNum));
        }
    }

    @Override
    public void run() {
        while (loop) {
            sell(); //  sell 方法是一个同步方法，
        }
    }
}


