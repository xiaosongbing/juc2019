package com.atguigu.sh.juc.juc2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @version 1.0
 * @ClassName: CyclicBarrierDemo
 * @Description: TODO
 * @Author jiguangpao
 * @Date: 2020/4/15 14:11
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("*******召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            final int template = i + 1;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第：" + template + "颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(template)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t 关门" );
    }
}
