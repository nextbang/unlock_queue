package com.nextbang.queue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nextbang on 17/1/23.
 */
public class RingBuffer {
    private static AtomicInteger productIndex = new AtomicInteger(0);

    private static AtomicInteger consumeIndex = new AtomicInteger(0);

    private static int MAX_LENGTH = 4;

    private static String[] dataArr = new String[MAX_LENGTH];

    private static final int maxSpinNums = 1000;

    public static boolean write(String content) {
        // 获取写入位置
        int oldWriteIndex = productIndex.get();

        // 追上一圈则写入失败
        if ((oldWriteIndex - consumeIndex.get()) >= MAX_LENGTH) {
            return false;
        }

        int indexAfterWrite = oldWriteIndex + 1;
        if (indexAfterWrite > Integer.MAX_VALUE) {
            indexAfterWrite = 0;
        }

        int spinNums = 0;
        for (;;) {
            if (spinNums++ >= maxSpinNums) {
                return false;
            }
            if (productIndex.compareAndSet(oldWriteIndex, indexAfterWrite)) {
                dataArr[oldWriteIndex & (MAX_LENGTH - 1)] = content;
                break;
            }
        }

        return true;
    }

    public static String read() {
        // 获取读取位置
        int oldReadIndex = consumeIndex.get();

        if (productIndex.get() <= oldReadIndex) {
            return null;
        }

        int indexAfterRead = oldReadIndex + 1;
        if (indexAfterRead > Integer.MAX_VALUE) {
            indexAfterRead = 0;
        }

        for (;;) {
            if (consumeIndex.compareAndSet(oldReadIndex, indexAfterRead)) {
                return dataArr[oldReadIndex & (MAX_LENGTH - 1)];
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(RingBuffer.write("a"));
        System.out.println(RingBuffer.read());
    }
}
