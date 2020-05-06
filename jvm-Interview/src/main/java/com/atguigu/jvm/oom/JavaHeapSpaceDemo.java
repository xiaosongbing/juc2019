package com.atguigu.jvm.oom;

/**
 * @version 1.0
 * @ClassName: JavaHeapSpaceDemo
 * @Description: 堆内存
 * @Author zzwang<br />
 * @Date: 2020/5/6 11:52
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        /*String str = "atguigu";
        while (true) {
            str += str + new Random().nextInt(11111111) + new Random().nextInt(22222222);
        }*/

        byte[] bytes = new byte[80 * 1024 * 1024];
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
