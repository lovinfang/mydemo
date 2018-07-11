package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
//            try {
//                while(!queue.isEmpty()) {   //并不是每次都监听是否为空，只能输出一个字符串
//                    System.out.println(queue.take());
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
