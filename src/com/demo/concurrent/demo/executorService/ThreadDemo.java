package com.demo.concurrent.demo.executorService;

public class ThreadDemo implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" executed");
    }
}
