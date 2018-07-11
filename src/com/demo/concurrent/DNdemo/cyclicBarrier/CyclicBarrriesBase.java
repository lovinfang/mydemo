package com.demo.concurrent.DNdemo.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrriesBase {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
                try {
                    c.await();//等待主线程完成
                    System.out.println(Thread.currentThread().getId()+"is going");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("sleeping...");
            }
        }).start();

        System.out.println("main will sleep.....");
        Thread.sleep(2000);
        c.await();//等待子线程完成

        System.out.println("All are complete.");
    }
}
