package com.demo.threadDemo;

/**
 *
 * @author lovin
 * @Date 2018/03/14
 * 测试阻塞
 *
 */
public class FlagCancel_two {

    private static class TestRunable implements Runnable{

        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            while(on){
                System.out.println(i++);  //on单独这样使用，并不能表示线程在on变成false的时候，线程就会中断
                /*阻塞方法，on不起作用
                  如wait,sleep,阻塞队列中的方法(put,take)
                */
                try {
                    synchronized (this){
                        wait();          //但是加入等待锁时，可以看出线程并没有中断，也就是单独使用on不起作用
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestRunable is runing :"+i);
        }

        public void cancel(){
            System.out.println("Ready stop thread......");
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestRunable testRunable = new TestRunable();
        Thread t = new Thread(testRunable);
        t.start();
        Thread.sleep(10);
        testRunable.cancel();
    }
}
