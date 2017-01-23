package com.nextbang.thread;

/**
 * Created by nextbang on 17/1/22.
 */
public class SingleThread {

    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        int num = 0;
        while(num<1_000_000){
            num++;
        }
        System.out.println(num);
        System.out.println("单线程耗时(s)：" + (System.currentTimeMillis() - begin));

    }
}
