package com.demo.threadDemo;

public class FlagCancel_unsafe {
    static class Flag extends Thread{
        //中断标志
        public static boolean flag = false;

        @Override
        public void run() {
            int i = 0;
            while(!flag){
                System.out.println(i++);
                if(i > 3){
                    try {
                        Thread.sleep(200);
                        interrupt();
                        if(i == 10){
                            cancel();//修改中断状态，退出线程
                        }
                        System.out.println("thread:"+ isInterrupted());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("www.baidu.com");
                }
            }
        }

        public static void cancel(){
            flag = true;
        }
    }

    public static void main(String[] args) {
        Flag test = new Flag();
        test.start();
        test.setPriority(10); //这里设置的优先级其实没什么用，CPU不会搭理你的
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main:"+test.isInterrupted());
    }
}
