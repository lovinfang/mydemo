package com.demo.concurrent.DNdemo.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchCase {


    static CountDownLatch c = new CountDownLatch(7);

    private static class SubThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId());
            c.countDown();
            System.out.println(Thread.currentThread().getId()+" is done");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
                c.countDown();
                System.out.println("sleeping...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep is completer");
                c.countDown();
            }
        }).start();

        for(int i=0;i<=4;i++){
            Thread thread = new Thread(new SubThread());
            thread.start();
        }

        c.await();
        System.out.println("Main will gone.....");
    }
}
