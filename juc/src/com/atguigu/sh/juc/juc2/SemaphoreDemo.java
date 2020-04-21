package com.atguigu.sh.juc.juc2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName: SemaphoreDemo
 * @Description:
 * 控制多线程的并发数
 * @Author jiguangpao
 * @Date: 2020/4/15 14:23
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟资源类有3个空车位

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢占到了车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
