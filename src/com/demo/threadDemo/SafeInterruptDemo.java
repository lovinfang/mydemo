package com.demo.threadDemo;

public class SafeInterruptDemo extends Thread{

    private boolean flag = false;

    @Override
    public void run() {
        int i = 0;
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().isInterrupted());
        while(!flag && !Thread.currentThread().isInterrupted()){
            System.out.println(i++);
            try{
                synchronized (this){
                    if(i > 3){
                        wait();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void cancel(Thread t){
        System.out.println("Ready stop currentThread...");
        flag = true;
        //将需要中断的线程的中断标志位设置为true
        t.interrupt();
        System.out.println(t.getName()+":"+t.isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        SafeInterruptDemo demo = new SafeInterruptDemo();
        demo.start();
        Thread.sleep(100);
        demo.cancel(demo);
    }
}
