package com.atguigu.sh.juc.ms;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @ClassName: CASDemo ==> compareAndSet
 * @Description:
 * 1. CAS 是什么么？
 *      - 比较并替换
 * @Author zzwang
 * @Date: 2020/4/23 13:52
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data:" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2048) + "\t current data:" + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
