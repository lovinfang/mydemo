package com.demo.concurrent.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample implements Runnable{

    private static int i;

    private String name;

    private  static Lock lock = new ReentrantLock();

    public LockExample(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while(true){
            if(i<=50){
                addI();
            }else{
                break;
            }
        }
    }

    private void addI(){
        lock.lock();
        System.out.println(this.name + " i="+i);
        i++;
        System.out.println(this.name + " i="+i);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        lock.unlock();
    }

    public static void main(String[] args) {
        LockExample lock1 = new LockExample("thread-1");
        LockExample lock2 = new LockExample("thread-2");
        LockExample lock3 = new LockExample("thread-3");
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        Thread t3 = new Thread(lock3);
        t1.start();
        t2.start();
        t3.start();
    }
}
