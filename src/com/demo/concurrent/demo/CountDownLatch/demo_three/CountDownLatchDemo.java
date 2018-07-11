package com.demo.concurrent.demo.CountDownLatch.demo_three;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    private CountDownLatch cd = new CountDownLatch(2);
    private Random rnd = new Random();

    class FirstTask implements Runnable{

        private String id;

        public FirstTask(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Thread "+ id + " is start");

            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread "+ id + " is over");

            cd.countDown();
        }
    }

    class SecondTask implements Runnable{

        private String id;

        public SecondTask(String id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                cd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("----------Thread "+ id + " is start");

            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------Thread "+ id + " is over");
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        CountDownLatchDemo cd = new CountDownLatchDemo();
        es.submit(cd.new SecondTask("c"));
        es.submit(cd.new SecondTask("d"));
        es.submit(cd.new FirstTask("a"));
        es.submit(cd.new FirstTask("b"));
        es.shutdown();
    }
}
