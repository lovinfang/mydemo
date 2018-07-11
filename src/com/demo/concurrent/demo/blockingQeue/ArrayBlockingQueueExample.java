package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) {
        try {
            BlockingQueue queue = new ArrayBlockingQueue(1024);
            queue.put("1");
            Object object = queue.take();
            System.out.println(object);

            BlockingQueue<String> queueStr = new ArrayBlockingQueue<String>(1024);
            queueStr.put("2");
            System.out.println(queueStr.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

