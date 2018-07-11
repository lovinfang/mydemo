package com.demo.concurrent.demo.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            Thread t = new Thread(new ThreadDemo());
            executorService.submit(t);
        }
        executorService.shutdownNow();
    }
}
