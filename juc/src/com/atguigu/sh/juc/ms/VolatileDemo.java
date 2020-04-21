package com.atguigu.sh.juc.ms;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @ClassName: VolatileDemo <br/>
 * @Description: TODO<br />
 * @Author zzwang<br />
 * @Date: 2020/4/20 16:31<br/>
 */
class MyData {
    volatile int number = 0;
    public void addTO60() {
        this.number = 60;
    }

    //number 已经给volatile修饰 (synchronized:太重)
    public void addPlusPlus() {
        number++;
    }

    //JUC --
    AtomicInteger atomicInteger = new AtomicInteger();
    public void atomicAdd() {
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1.验证volatile 的可见性
 *   1.1 假如int number = 0; number变量之前根本没有添加volatile关键字修饰, 没有可见性
 *   1.2 添加了volatile, 可以解决可见性问题;
 *
 * 2. 验证volatile不保证原子性
 *   2.1 原子性指的是什么意思？
 *      不可分割, 完整性, 也即某个线程正在做某个具体业务时, 中间不可以被加塞或者被分割. 需要整体完整
 *      要么同时成功, 要么同时失败.
 *   2.2 是否保证原子性? ---否
 *   2.3 why
 *   2.4 如何解决原子性?
 *    * 加synchronized
 *    * 使用juc下的AutomicInteger
 *
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <=20 ; i++) {
        	new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.atomicAdd();
                }
        	}, String.valueOf(i)).start();
        }

        //20个线程全部over
//        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
        while (Thread.activeCount() > 2) { //若还有线程执行
            Thread.yield(); //等待
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value:" + myData.atomicInteger);
    }

    /**
     * volatile 可见性
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            myData.addTO60();
            System.out.println(Thread.currentThread().getName() + "\t update number value : " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
            //main 线程就一直等待循环
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over , main get value: " + myData.number);
    }
}
