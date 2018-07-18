package com.demo.threadDemo.interrupt;

/**
 *
 * 创建日期：2017/11/26
 * 创建时间: 21:02
 * 调用阻塞方法时，如何中断线程
 */
public class BlockInterrupt {

    private static Object o = new Object();


    /*while循环中包含try/catch块*/
    private static class WhileTryWhenBlock extends Thread {
        private volatile boolean on = true;
        private long i =0;
        @Override
        public void run() {
            System.out.println("当前执行线程id："+Thread.currentThread().getId());
            while (on && !Thread.currentThread().isInterrupted()) {
                System.out.println("i="+i++);
                try {
                    //抛出中断异常的阻塞方法，抛出异常后，中断标志位会改成false
                    //可以理解为这些方法会隐含调用Thread.interrputed()方法
                    synchronized (o){
                        o.wait();    //当线程持有此锁处于等待唤醒的状态，如果这个时候执行线程中断操作，则会抛出异常，并且中断标志位设置为false(不中断)
                    }
                } catch (InterruptedException e) {
                    System.out.println("当前执行线程的中断标志位："
                            +Thread.currentThread().getId()
                            +":"+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();//重新设置一下，如果不设置的话，那么线程还是不中断的，那么还是会执行到while循环中
                    System.out.println("被中断的线程_"+getId()
                            +":"+isInterrupted());
                    //do my work
                }
                //清理工作，准备结束线程
            }
        }

        public void cancel() {
            interrupt(); //中断非main线程
            System.out.println("本方法所在线程实例："+getId());
            System.out.println("执行本方法的线程："+Thread.currentThread().getId()); //其实是main方法中的 whileTryWhenBlock调用的所以跟上一行打印的不一样
            //Thread.currentThread().interrupt();   这时候可能会将main线程进行中断
        }
    }

    /*try/catch块中包含while循环*/
    private static class TryWhileWhenBlock  extends Thread {
        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            try {
                while (on) {
                    System.out.println(i++);
                    //抛出中断异常的阻塞方法，抛出异常后，中断标志位改成false
                    synchronized (o){
                        o.wait();   //线程等待的时候不会释放锁
                    }
                }
                System.out.println("线程已经执行完毕。。。"); //不会执行
            } catch (InterruptedException e) {   //这个时候抛出异常，表示run方法已经执行完了，线程也结束了，所以不会再执行了
                System.out.println("当前执行线程的中断标志位："
                        +Thread.currentThread().getId()
                        +":"+Thread.currentThread().isInterrupted());
            }finally {
                //清理工作结束线程
            }
        }
        public void cancel() {
            on = false;
            interrupt(); //当线程在执行等待的时候执行中断线程，则会抛出异常
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        WhileTryWhenBlock whileTryWhenBlock = new WhileTryWhenBlock();
//        whileTryWhenBlock.start();
//        Thread.sleep(100);
//        whileTryWhenBlock.cancel();


        TryWhileWhenBlock tryWhileWhenBlock = new TryWhileWhenBlock();
        tryWhileWhenBlock.start();
        Thread.sleep(100);
        tryWhileWhenBlock.cancel();
    }
}
