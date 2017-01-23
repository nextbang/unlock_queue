package com.nextbang.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nextbang on 17/1/22.
 */
public class MultiThread {


    public static void main(String[] args){
        long begin = System.currentTimeMillis();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        double maxValue = Math.pow(10, 6);
        for(int i=0;i<maxValue;i++){
            fixedThreadPool.execute(new AddThread());
        }
        fixedThreadPool.shutdown();
        if(fixedThreadPool.isShutdown()){
            System.out.println("多线程耗时(s)：" + (System.currentTimeMillis() - begin));
        }
    }
}

class AddThread implements Runnable{

    private static AtomicInteger num = new AtomicInteger(0);

    public void run(){
//        System.out.println(num.incrementAndGet());
        num.incrementAndGet();
    }
}

