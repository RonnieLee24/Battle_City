package Thread_use;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/6/27 - 06 - 27 - 23:56
 * @Description: Thread_use
 * @version: 1.0
 * 通过实现接口 Runnable 来开发线程
 */
public class Thread02 {
    public static void main(String[] args) {
//        new Dog().start()   -->  这里不能调用 start() 方法
        //  创建了Thread对象，把 dog对象（实现了 Runnable接口），放入 Thread
//        Thread thread = new Thread(new Dog());
//        thread.start();

    }
}


//  线程代理类，模拟了一个极简的 Thread 类
class ThreadProxy implements Runnable{    //  你可以把 Proxy类当做 ThreadProxy (买票代理。跑腿的)

    private Runnable target = null; //  属性，类型是 Runnable

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start(){
        start0();   //  这个方法是真正实现多线程的方法
    }

    public void start0(){
        run();
    }
}

class Dog implements Runnable { //  通过实现 Runnable 接口，开发线程

    int count = 0;
    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫！！！hi" + (++count) + Thread.currentThread().getName());

            //  休眠1秒钟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 10) {
                break;
            }
        }
    }
}
