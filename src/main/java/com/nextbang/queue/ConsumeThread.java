package com.nextbang.queue;

import com.sun.tools.internal.xjc.reader.Ring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nextbang on 17/1/23.
 */
public class ConsumeThread extends Thread{

    public void run(){
        System.out.println(RingBuffer.read());
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ConsumeThread());
    }
}
