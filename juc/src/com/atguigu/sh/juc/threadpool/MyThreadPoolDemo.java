package com.atguigu.sh.juc.threadpool;

import java.util.concurrent.*;

/**
 * @version 1.0
 * @ClassName: MyThreadPoolDemo
 * @Description: TODO
 * @Author jiguangpao
 * @Date: 2020/4/15 16:48
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService pool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            //模拟有10个顾客过银行办理业务, 目前池子有5个工作人员提供服务
            for (int i = 1; i <= 9; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");

                });

                //暂停
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }


    }

    public static void initExecutors() {
        //创建5个受理线程....
//        ExecutorService pool = Executors.newFixedThreadPool(5);
//        ExecutorService pool = Executors.newSingleThreadExecutor();//一池只有一个1工作线程

        ExecutorService pool = Executors.newCachedThreadPool(); //一池N线程

        try {
            //模拟有10个顾客过银行办理业务, 目前池子有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");

                });

                //暂停
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}
