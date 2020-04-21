package com.atguigu.sh.juc.juc1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @ClassName: NotSafeDemo
 * @Description: TODO
 * 题目： 请举例说明集合类是不安全的
 *
 * 故障原因：
 *
 *
 * @Author jiguangpao
 * @Date: 2020/4/14 16:18
 */
public class NotSafeDemo {

    public static void main(String[] args) {

        toMap();
    }

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

    public static void toCopyOnWriteArrayList() {

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
