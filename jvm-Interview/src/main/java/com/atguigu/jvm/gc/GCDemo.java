package com.atguigu.jvm.gc;

/**
 * @version 1.0
 * @ClassName: GCDemo
 * @Description: TODO
 * @Author zzwang<br />
 * @Date: 2020/5/7 15:39
 */

import java.util.Random;

/**
 * 新生代:
 * 1.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  (DefNew+Tenured)
 *
 * 2.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew+Tenured)
 * 备注情况: Java HotSpot(TM) 64-Bit Server VM warning:
 * Using the ParNew young collector with the Serial old collector is deprecated
 * and will likely be removed in a future release
 *
 * 3.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC (PSYoungGen+ParOldGen)
 *
 * 老年代:
 * 4.
 * 4.1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 * 4.2 不加就是默认useParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * 5.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 * (par new generation + concurrent mark-sweep generation)
 *
 * 6.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 * 7.(理论知道即可, 实际中java8已经被优化掉了, 没有了.)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 */
public class GCDemo {
    public static void main(String[] args) {
        System.out.println("*********HelloGC*********");
//        Thread.sleep(Integer.MAX_VALUE);
//        byte[] bytes = new byte[100 * 1024 * 1024];
        String str = "atguigu";
        while (true) {
            str += str + new Random().nextInt(77777) + new Random().nextInt(88888);
            str.intern();
        }
    }
}
