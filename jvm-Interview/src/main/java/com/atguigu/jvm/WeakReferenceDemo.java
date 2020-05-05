package com.atguigu.jvm;

import java.lang.ref.WeakReference;

/**
 * @ClassName WeakReferenceDemo
 * @Description
 * @Author wangzuzhen
 * @Create 2020-05-05 22:23
 * @Version 1.0
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("*************");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
