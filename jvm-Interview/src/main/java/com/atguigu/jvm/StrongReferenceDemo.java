package com.atguigu.jvm;

/**
 * @ClassName StrongReferenceDemo
 * @Description 强引用:死也不收
 * @Author wangzuzhen
 * @Create 2020-05-05 22:02
 * @Version 1.0
 */
//死也不收
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();// 定义默认的就是强引用
        Object obj2 = obj1; //obj2引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
