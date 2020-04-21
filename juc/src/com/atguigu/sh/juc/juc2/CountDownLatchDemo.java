package com.atguigu.sh.juc.juc2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName: CountDownLatchDemo
 * @Description: TODO
 * 开会关门
 * @Author jiguangpao
 * @Date: 2020/4/15 13:59
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 走人");
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 关门" );
    }

    public static void closeDoor() {
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 走人");
            }, String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t 关门" );
    }
}

