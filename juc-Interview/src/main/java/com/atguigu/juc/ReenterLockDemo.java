package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReenterLockDemo
 * @Description
 * 可重入锁(也即递归锁)
 * 指的同一个线程在外层函数获得锁时，内层函数递归仍然能获取该锁的代码
 * 在同一个线程在外层方法获得锁时，进入内层方法会自动获取锁
 * 也就是说，线程可以进入任何一个它已经拥有锁的代码块。
 *
 * case 1: synchronized 也是一个典型的可重入锁
 * t1	 invoked sendSMS()
 * t1	 invoked sendEmail()
 * t2	 invoked sendSMS()
 * t2	 invoked sendEmail()
 *
 * case2: ReentrantLock 是一个典型的可重入锁
 * t4	 invoked get()
 * t4	 invoked set()
 * t3	 invoked get()
 * t3	 invoked set()
 *
 *
 * @Author wangzuzhen
 * @Create 2020-04-26 15:04
 * @Version 1.0
 */

class Phone implements Runnable{

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    //===========Lock=============
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
//        lock.lock(); 编译正确, 运行正确, 锁对应
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        }catch (Exception e) {

        } finally {
            lock.unlock();
//            lock.unlock();
        }
    }
    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        }catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

        new Thread(phone, "t3").start();
        new Thread(phone, "t4").start();


    }
}
