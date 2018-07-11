package com.demo.concurrent.demo.CountDownLatch.demo_two.impl;

import com.demo.concurrent.demo.CountDownLatch.demo_two.BaseHealthChecker;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch latch) {
        super("Database Service",latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking "+this.getServiceName());
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName()+" is UP");
    }
}
