package com.atguigu.jvm;

import java.lang.ref.SoftReference;

/**
 * @ClassName SoftReferenceDemo
 * @Description 软引用
 * @Author wangzuzhen
 * @Create 2020-05-05 22:07
 * @Version 1.0
 */

public class SoftReferenceDemo {

    //内存够用时保留, 不够用就回收!
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(softReference.get());
    }

    /**
     * JVM配置: 故意产生大对象并配置小的内存, 让它内存不够用了导致OOM, 看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */

    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;

        try {
            byte[] bytes = new byte[50 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

        System.gc();
        System.out.println(softReference.get());
    }

    public static void main(String[] args) {
        softRef_Memory_Enough();
//        softRef_Memory_NotEnough();
    }
}
