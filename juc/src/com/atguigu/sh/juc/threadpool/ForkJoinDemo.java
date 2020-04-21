package com.atguigu.sh.juc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @version 1.0
 * @ClassName: ForkJoinDemo
 * @Description:
 *  分支合并框架
 *
 * @Author jiguangpao
 * @Date: 2020/4/16 15:05
 */

class MyTask extends RecursiveTask<Integer> {

    public static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }

        return result;
    }
}

public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask task = new MyTask(0, 100);
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Integer> submit = pool.submit(task);
        System.out.println(submit.get());
        pool.shutdown();
    }
}
