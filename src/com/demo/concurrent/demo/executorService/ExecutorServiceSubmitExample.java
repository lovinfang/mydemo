package com.demo.concurrent.demo.executorService;

import java.util.concurrent.*;

public class ExecutorServiceSubmitExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future future = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println(future.get());
    }
}
