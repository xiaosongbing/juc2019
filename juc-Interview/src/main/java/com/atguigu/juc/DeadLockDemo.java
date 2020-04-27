package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName: DeadLockDemo
 * @Description: 死锁
 * 死锁是指两个或两个以上的进程在执行过程中,
 * 因争夺资源而造成的一种互相等待的现象,
 * 若无外力干涉那它们都将无法推进下去.
 * @Author zzwang<br />
 * @Date: 2020/4/27 10:07
 */

class HoldLockThread implements Runnable {

    String lockA;
    String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t尝试获得：" + lockA);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new HoldLockThread("lockA", "lockB"), "TheadA").start();
        new Thread(new HoldLockThread("lockB", "lockA"), "TheadB").start();
    }
}
