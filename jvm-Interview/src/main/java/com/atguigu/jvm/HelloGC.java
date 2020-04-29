package com.atguigu.jvm;

/**
 * @version 1.0
 * @ClassName: HelloGC
 * @Description: TODO
 * @Author zzwang<br />
 * @Date: 2020/4/27 11:50
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*********HelloGC*********");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
