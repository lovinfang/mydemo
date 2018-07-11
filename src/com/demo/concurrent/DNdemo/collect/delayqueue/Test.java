package com.demo.concurrent.DNdemo.collect.delayqueue;

import java.util.concurrent.DelayQueue;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<CacheBean<User>> queue = new DelayQueue<CacheBean<User>>();
        new Thread(new PutInCache(queue)).start();
        new Thread(new GetFromCache(queue)).start();

        for(int i=1;i<20;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
