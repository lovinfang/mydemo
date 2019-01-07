package com.demo.threadDemo.interrupt;

/**
 *
 * 创建日期：2017/11/26
 * 创建时间: 20:42
 * 安全的中断线程
 */
public class SafeInterrupt implements Runnable{

    private volatile boolean on = true;
    private long i =0;

    @Override
    public void run() {
        while(on && Thread.currentThread().isInterrupted()){
            i++;
            System.out.println("TestRunable is runing --- :"+i);
        }
        System.out.println("TestRunable is runing :"+i);
    }

    public void cancel(){
        on = false;
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args)throws Exception {
        SafeInterrupt safeInterrupt = new SafeInterrupt();
        Thread t = new Thread(new SafeInterrupt());
        t.start();

        Thread.sleep(10000);
        safeInterrupt.cancel();
    }
}
