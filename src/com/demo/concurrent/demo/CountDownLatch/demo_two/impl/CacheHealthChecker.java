package com.demo.concurrent.demo.CountDownLatch.demo_two.impl;

import com.demo.concurrent.demo.CountDownLatch.demo_two.BaseHealthChecker;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch latch) {
        super("CacheHealth Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking "+this.getServiceName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName()+" is UP");
    }
}
