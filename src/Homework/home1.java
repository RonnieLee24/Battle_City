package Homework;

import java.util.Scanner;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/7/10 - 07 - 10 - 15:33
 * @Description: Homework
 * @version: 1.0
 */
public class home1 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a.start();
        b.start();

    }
}

class A extends Thread{
    private Boolean flag = true;
    @Override
    public void run() {
        //  输出 1 - 100 数字
        while (flag){
            System.out.println((int)(Math.random() * 100 + 1));
            //  休眠 1s
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(Boolean flag) { //  可以修改 loop 变量
        this.flag = flag;
    }
}

//  直到第2个线程从键盘读取了 "Q" 命令
class B extends Thread{
    //  我们要在 B 中持有 A 的对象
    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) { //  在构造器中，直接传入A类对象
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            //  接受到用户的输入
            System.out.println("请输入你的指令(Q)，表示退出");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q'){
                //  以通知的方式结束 a 线程
                a.setFlag(false);
                System.out.println("b线程退出");
                break;
            }
        }
    }
}
