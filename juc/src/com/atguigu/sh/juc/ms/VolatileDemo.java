package com.atguigu.sh.juc.ms;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName: VolatileDemo <br/>
 * @Description: TODO<br />
 * @Author zzwang<br />
 * @Date: 2020/4/20 16:31<br/>
 */
class MyData {
    int number = 0;
    public void addTO60() {
        this.number = 60;
    }
}

/**
 * 1.验证volatile 的可见性
 *
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTO60();
            System.out.println(Thread.currentThread().getName() + "\t update number value : " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
            //main 线程就一直等待循环

        }
    }
}
