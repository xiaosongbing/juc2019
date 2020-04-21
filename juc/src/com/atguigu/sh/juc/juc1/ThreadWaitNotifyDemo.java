package com.atguigu.sh.juc.juc1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName: ThreadWaitNotifyDemo
 * @Description:
 *
 *
 * 2.判断/干活/通知
 * 3.多线程交互中, 必须要防止多线程的虚假唤醒, 也即(判断只用while, 不能用if)
 *
 * <p>
 * 生产者, 消费者
 * 实现一个线程对该变量加1， 一个线程对该变量减1
 * 实现交替, 来10论, 变量初始值为零.
 * @Author jiguangpao
 * @Date: 2020/4/14 14:27
 */

class AirConditioner { //资源类
    private int number = 0;

    private Lock lock = new ReentrantLock();
//    Condition conditionA = lock.newCondition();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException{
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }

            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException{
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*public synchronized void increment() throws InterruptedException {
        //1.判断 /多线程交互 synchronized 中 用while   不能用if
        while (number != 0) {
            this.wait();
        }

        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {

        //1.判断
        while (number == 0) {
            this.wait();
        }

        //2.干活
        number--;

        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3.通知
        this.notifyAll();

    }*/


}

public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {
        testSynchronizedDemo();

    }

    public static void testSynchronizedDemo() {
        AirConditioner ac = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    ac.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    ac.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    ac.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    ac.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}


