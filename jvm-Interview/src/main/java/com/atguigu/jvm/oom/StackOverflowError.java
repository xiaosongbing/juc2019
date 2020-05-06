package com.atguigu.jvm.oom;

/**
 * @version 1.0
 * @ClassName: StackOverflowError
 * @Description: 栈内存溢出
 * @Author zzwang<br />
 * @Date: 2020/5/6 11:47
 */
public class StackOverflowError {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }

}
