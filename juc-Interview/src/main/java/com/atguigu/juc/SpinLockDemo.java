package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName spinLockDemo
 * @Description
 * 自旋锁：
 * 自旋锁好处： 循环比较获取直到成功为止, 没有类似wait的阻塞.
 *
 * 通过CAS操作完成自旋锁, A线程先进来调用myLock方法自己持有锁5秒钟,
 * B随后进来后发现当前有线程持有锁, 不是null, 所以只能通过自旋等待,
 * 直到A释放锁后B随后抢到。
 * @Author wangzuzhen
 * @Create 2020-04-26 15:49
 * @Version 1.0
 */

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in myLock");
        while(!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t come in myUnLock");

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        }, "AA").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        }, "BB").start();
    }
}
