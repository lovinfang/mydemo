package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> linkedQueue = new LinkedBlockingDeque<String>();
        linkedQueue.addFirst("1");
        linkedQueue.addLast("2");

        String two = linkedQueue.takeLast();
        String one = linkedQueue.takeFirst();
        System.out.println(two);
        System.out.println(one);
    }
}
