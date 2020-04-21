package com.atguigu.sh.juc.juc1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName: SaleTicket
 * @Description: 题目： 三个售票员  卖出30张票
 * 多线程编程的企业级套路+模板
 * 1.在高内聚低耦合的前提下,  线程   操作(对外暴露的调用方法)   资源类
 * @Author jiguangpao
 * @Date: 2020/4/14 11:03
 */

class Ticket { // 资源类

    private int number = 300;
    private Lock lock = new ReentrantLock();

    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第:" + (number--) + "\t 还剩下:" + number);
        }
    }

    public void saleTicketLock() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第:" + (number--) + "\t 还剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

//        Thread.State;

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 400; i++) {
                    ticket.saleTicketLock();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 400; i++) {
                    ticket.saleTicketLock();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 400; i++) {
                    ticket.saleTicketLock();
                }
            }
        }, "C").start();*/

        new Thread(() -> {
            for (int i = 0; i <= 400; i++) {
                ticket.saleTicketLock();
            }
        }, "A").start();

        new Thread(() -> {for (int i = 0; i <= 400; i++) ticket.saleTicketLock(); }, "B").start();
        new Thread(() -> {for (int i = 0; i <= 400; i++) ticket.saleTicketLock(); }, "C").start();
    }
}
