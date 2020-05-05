package com.atguigu.jvm;

import sun.java2d.pipe.SpanIterator;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @ClassName WeakHashMapDemo
 * @Description 软引用中的WeakHashMap
 * @Author wangzuzhen
 * @Create 2020-05-05 22:43
 * @Version 1.0
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("===========================");
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, Object> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void myHashMap() {
        HashMap<Integer, Object> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
