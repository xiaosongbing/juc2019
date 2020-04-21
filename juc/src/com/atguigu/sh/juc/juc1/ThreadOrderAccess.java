package com.atguigu.sh.juc.juc1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName: ThreadOrderAccess
 * @Description: TODO
 * 4.标志位
 *
 * 多线程之间按顺序调用, 实现A-> B ->C
 * 三个线程启动, 要求如下：
 *
 * AA 打印5次, BB打印5次, CC打印15次
 * 接着
 * AA 打印5次, BB打印5次, CC打印15次
 * 来10轮
 * @Author jiguangpao
 * @Date: 2020/4/14 15:10
 */

class OrderRes {

    private int flag = 1; //A: 1, B:2, C:3

    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void printAA() {
        lock.lock();
        try {
            while (flag != 1) {
                conditionA.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "AA" + i);
            }

            flag = 2;
            conditionB.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printBB() {
        lock.lock();
        try {
            while (flag != 2) {
                conditionB.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "BB"  + i);
            }

            flag = 3;
            conditionC.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printCC() {
        lock.lock();
        try {
            while (flag != 3) {
                conditionC.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + "CC"  + i);
            }

            flag = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


public class ThreadOrderAccess {

    public static void main(String[] args) {

        OrderRes orderRes = new OrderRes();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                orderRes.printAA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                orderRes.printBB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                orderRes.printCC();
            }
        }, "C").start();

    }
}
