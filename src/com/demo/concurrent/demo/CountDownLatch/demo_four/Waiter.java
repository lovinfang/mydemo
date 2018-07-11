package com.demo.concurrent.demo.CountDownLatch.demo_four;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable{
    CountDownLatch latch = null;
    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}
