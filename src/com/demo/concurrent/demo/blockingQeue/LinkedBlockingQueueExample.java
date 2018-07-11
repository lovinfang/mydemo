package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> unbounded = new LinkedBlockingDeque<String>(Integer.MAX_VALUE);
        BlockingQueue<String> bounded = new LinkedBlockingDeque<>(1024);

        bounded.put("Value");
        System.out.println(unbounded.size());
        System.out.println(bounded.size());

        System.out.println(bounded.take());
    }
}
