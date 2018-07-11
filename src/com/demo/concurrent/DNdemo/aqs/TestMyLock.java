package com.demo.concurrent.DNdemo.aqs;

import com.demo.concurrent.DNdemo.util.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMyLock {
    public void test() {
//        final Lock lock = new ReentrantLock();
//        final Lock lock = new SingleLock();
        final Lock lock = new TwoLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                    SleepUtils.second(2);
                }
            }
        }
        // 启动10个子线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            //w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(3);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
