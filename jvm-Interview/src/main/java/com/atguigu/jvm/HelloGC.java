package com.atguigu.jvm;

/**
 * @version 1.0
 * @ClassName: HelloGC
 * @Description:
 * @Author zzwang<br />
 * @Date: 2020/4/27 11:50
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*********HelloGC*********");
//        Thread.sleep(Integer.MAX_VALUE);

        /*long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存总量(默认约物理内存的64分之一)
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量(默认约物理内存的4分之一)

        System.out.println("TOTAL_MEMORY(-xms)=" + totalMemory + "(字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
        System.out.println("MAX_MEMORY(-xms)=" + maxMemory + "(字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");*/

//        byte[] bytes = new byte[50 * 1024 * 1024];


    }
}
