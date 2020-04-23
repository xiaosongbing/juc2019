package com.atguigu.sh.juc.ms;

/**
 * 指令重排
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    //多线程环境中交替执行, 由于编译器优化重排的存在，
    //两个线程中使用的变量能否保证一致性是无法确定的, 结果无法预测
    public void method2() {
        if(flag) {
            a = a + 5;
            System.out.println("************** retValue: " + a);


        }
    }
}
