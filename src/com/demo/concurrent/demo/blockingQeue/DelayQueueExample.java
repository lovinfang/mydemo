package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue queue = new DelayQueue();

        DelayedElement element1 = new DelayedElement();
        element1.setName("fyw");
        queue.put(element1);

        Delayed element2 = queue.take();
        System.out.println(element2.getDelay(TimeUnit.SECONDS));

        queue.put(element1);
        DelayedElement element3 = (DelayedElement) queue.take();
        System.out.println(element3.getName());
    }
}
