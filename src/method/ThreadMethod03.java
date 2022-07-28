package method;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/3 - 07 - 03 - 13:37
 * @Description: method
 * @version: 1.0
 */
public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {

        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //  如果我们希望当 main 线程结束后，子线程自动结束
        //  只需将子线程设置为 守护线程即可
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("唱跳 Rap 篮球");
            Thread.sleep(1000);
        }

    }
}

class MyDaemonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);   //  休眠1000 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("鸡你太美！！！~~~");

        }
    }
}
