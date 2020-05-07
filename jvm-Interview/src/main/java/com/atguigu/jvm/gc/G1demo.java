package com.atguigu.jvm.gc;

/**
 * @version 1.0
 * @ClassName: G1demo
 * @Description: TODO
 * @Author zzwang<br />
 * @Date: 2020/5/7 15:19
 */

import java.util.Random;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC
 */
public class G1demo {
    public static void main(String[] args) {
        String str = "atguigu";
        while (true) {
            str += str + new Random().nextInt(77777) + new Random().nextInt(88888);
            str.intern();
        }
    }
}
