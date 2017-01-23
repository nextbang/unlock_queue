package com.nextbang.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nextbang on 17/1/23.
 */
public class ProductThread extends Thread{

    private String content=null;

    public ProductThread(String content){
        this.content = content;
    }

    public void run(){
        System.out.println(RingBuffer.write(content));
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<10;i++){
            executorService.execute(new ProductThread("" + i));
        }
    }
}
