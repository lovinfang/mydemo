package com.demo.completionService;

import java.util.concurrent.*;

/**
 * 创建日期：2017/12/10
 * 创建时间: 21:57
 */
public class CompletionTest {
    private final int POOL_SIZE  = 5;
    private final int TOTAL_TASK = 10;

    // 方法一，自己写集合来实现获取线程池中任务的返回结果
    public void testByQueue() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<>();
        // 向里面扔任务
        for(int i=0;i<TOTAL_TASK;i++){
            Future<String> future = pool.submit(new WorkTask("ExecTask"+i));
            queue.add(future);
        }

        // 检查线程池任务执行结果
        for(int i=0;i<TOTAL_TASK;i++){
            System.out.println("ExecTask:"+queue.take().get());
        }

        pool.shutdown();

    }

    // 方法二，通过CompletionService来实现获取线程池中任务的返回结果
    public void testByCompletion() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<String> service = new ExecutorCompletionService<String>(pool);

        // 向里面扔任务
        for(int i=0;i<TOTAL_TASK;i++){
            service.submit(new WorkTask("ExecTask"+i) );
        }

        // 检查线程池任务执行结果
        for(int i=0;i<TOTAL_TASK;i++){
            Future<String> future = service.take();
            System.out.println("CompletionService:"+future.get());
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionTest  completionTest = new CompletionTest();

        /**
         * completionTest.testByQueue();
         * 提交到线程池中的线程执行顺序是随机的，但是获取结果则是按照放入线程池的线程的顺序来取
         * 这个时候如果靠后的线程执行速度比较快，但是获取结果也要排队拿，哪怕他前面的线程执行速度慢
         * 也要等前面的线程执行完拿完结果之后才能拿到值
         */
        //completionTest.testByQueue();

        /**
         * completionTest.testByCompletion(); 按照线程执行的顺序来取值，但是实际测试结果不是这样的
         */
        completionTest.testByCompletion();
    }

}
