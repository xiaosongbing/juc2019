package com.atguigu.jvm;

/**
 * @version 1.0
 * @ClassName: GCRootDemo
 * @Description: 在java中, 可作为GCRoot的对象有:
 * 1.虚拟机栈(栈帧中的本地变量表)中引用的对象；
 * 2.方法区中的类静态属性引用的对象；
 * 3.方法区中常量引用的对象；
 * 4.本地方法栈中JNI(即一般说得Native方法)中引用的对象
 * @Author zzwang<br />
 * @Date: 2020/4/27 11:24
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100 * 1024 * 1024];
//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3 = new GCRootDemo3();

    public static void m1() {
        GCRootDemo g1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
