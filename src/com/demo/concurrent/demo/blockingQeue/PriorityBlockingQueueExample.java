package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue   = new PriorityBlockingQueue();
        queue.put("Value");
        String value = (String) queue.take();
        System.out.println(value);
    }
}
