package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version 1.0
 * @ClassName: ABADemo
 * @Description: TODO
 * @Author zzwang<br />
 * @Date: 2020/4/23 17:35
 */
public class ABADemo { //ABA问题的解决     AtomicStampedference


    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("============ABA问题的产生=============");

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            //暂停1秒钟t2线程, 保证上面的t1线程完成了一次ABA操作
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get().toString());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("============ABA问题的解决=============");


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号:" + stamp);

            //暂停1秒钟t3线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号:" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 第3次版本号:" + atomicStampedReference.getStamp());

        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号:" + stamp);

            //暂停3秒钟t4线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);

            System.out.println(Thread.currentThread().getName() + "\t是否修改成功:" + result + "\t当前最新实际版本号:" +  atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t 当前实际最新值:" + atomicStampedReference.getReference());
        }, "t4").start();

    }
}
