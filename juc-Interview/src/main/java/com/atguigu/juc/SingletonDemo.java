package com.atguigu.juc;

/**
 * 单例模式
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()  + "\t 构造方法..." );
    }

    // 加synchronized
    // DCL ： 双端检索机制
    public static SingletonDemo getInstance() {

        if(instance == null) {
            synchronized (SingletonDemo.class) {
                if(instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        for (int i = 1; i <= 50; i++) {
        	new Thread(() -> {
                SingletonDemo.getInstance();
        	}, String.valueOf(i)).start();
        }


    }

}
