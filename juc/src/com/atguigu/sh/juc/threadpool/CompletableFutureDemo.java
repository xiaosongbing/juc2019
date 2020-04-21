package com.atguigu.sh.juc.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @version 1.0
 * @ClassName: CompletableFutureDemo
 * @Description:
 * 异步回调
 * @Author jiguangpao
 * @Date: 2020/4/16 15:54
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture completableFuture = new CompletableFuture();

        /*CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值, updata mysql ok");
        });

        voidCompletableFuture.get();*/

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值, insert mysql ok");
            int age = 10 / 0;
            return 1024;
        });

        System.out.println(integerCompletableFuture.whenCompleteAsync((t, u) -> {
            System.out.println("*************t:" + t);
            System.out.println("*************u:" + u);
        }).exceptionally((f) -> {
            System.out.println("=====exception" + f.getMessage());
            return 333;
        }).get());


    }
}
