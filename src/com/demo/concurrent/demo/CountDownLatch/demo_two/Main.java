package com.demo.concurrent.demo.CountDownLatch.demo_two;

public class Main {

    public static void main(String[] args) {
        boolean result = false;

        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("External services validation completed !! Result was :: "+result);
    }
}
