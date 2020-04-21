package com.atguigu.sh.juc.juc1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName: CallableDemo
 * @Description: 多线程中, 第3种获得多线程的方式
 * @Author jiguangpao
 * @Date: 2020/4/15 11:27
 */
class Thread1 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("*********come in here");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        MyThread mt = new MyThread();

        FutureTask<Integer> futureTask = new FutureTask(new MyThread());

        //一个futureTask 对象, 只调用一次
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        System.out.println(Thread.currentThread().getName() + "----计算完成");

        Integer s = futureTask.get();
        System.out.println(s);

    }
}

