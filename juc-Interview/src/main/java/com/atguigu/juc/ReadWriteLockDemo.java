package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @version 1.0
 * @ClassName: ReadWriteLockDemo
 * @Description:
 * 多个线程同时读一个资源类没有任何问题, 所以为了满足并发量, 读取共享资源应该可以同时进行.
 * 但是
 * 如果有一个线程想去写共享资源， 就不应该再有其他线程可以对该资源进行读或写
 * 小总结：
 *      读-读  能共存
 *      读-写  不能共存
 *      写-写  不能共存
 * @Author jiguangpao
 * @Date: 2020/4/15 14:48
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key , Object value) {
        
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成" );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 读取数据" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache mc = new MyCache();

        for (int i = 1; i <= 5; i++) {

            final int temp = i;

            new Thread(() -> {
                mc.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }


        for (int i = 1; i <= 5; i++) {

            final int temp = i;

            new Thread(() -> {
                mc.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}
