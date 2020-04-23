package com.atguigu.juc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version 1.0
 * @ClassName: AtomicReferenceDemo
 * @Description: 原子引用
 * @Author zzwang<br />
 * @Date: 2020/4/23 16:48
 */

@Getter
@ToString
@AllArgsConstructor
class User {
    private Integer age;
    private String userName;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User(10, "z3");
        User li4 = new User(20, "li4");

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
    }
}
