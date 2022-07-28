package Cpu_num;

/**
 * @Author: Ronnie LEE
 * @Date: 2022/6/10 - 06 - 10 - 0:19
 * @Description: Cpu_num
 * @version: 1.0
 */
public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime(); //  底层是一个单例模式
        //  获取电脑的 cpu 数量 / 核心数
        int cpuNums = runtime.availableProcessors();
        System.out.println("CPU个数为：" + cpuNums);



    }
}
