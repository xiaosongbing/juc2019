package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName ContainerNotSaleDemo
 * @Description
 * 集合类不安全的问题
 * @Author wangzuzhen
 * @Create 2020-04-26 12:36
 * @Version 1.0
 */
public class ContainerNotSaleDemo {

    public static void toMap() {
//        HashMap<String, String> map = new HashMap<>();
//        HashMap<String, String> map2 = new HashMap<>(1000);

        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <=30 ; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }
    public static void toSet() {

//        Set<String> set = new HashSet<>();
        new HashSet<>();

//        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <=30 ; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
    public static void main(String[] args) {
//        listNotSafe();

    }

    public static void listNotSafe() {
        //        List<String> list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);

//        List<String> list = new ArrayList<>();
        //java.util.ConcurrentModificationException

//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 1; i <=30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
