package com.demo.concurrent.demo.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTaskExample extends RecursiveTask<Long> {

    private long workLoad = 0;

    public ForkJoinPoolTaskExample(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Long compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<ForkJoinPoolTaskExample> subtasks = new ArrayList<ForkJoinPoolTaskExample>();
            subtasks.addAll(createSubtasks());
            for(ForkJoinPoolTaskExample subtask : subtasks){
                subtask.fork();
            }
            long result = 0;
            for(ForkJoinPoolTaskExample subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<ForkJoinPoolTaskExample> createSubtasks() {
        List<ForkJoinPoolTaskExample> subtasks = new ArrayList<ForkJoinPoolTaskExample>();

        ForkJoinPoolTaskExample subtask1 = new ForkJoinPoolTaskExample(this.workLoad / 2);
        ForkJoinPoolTaskExample subtask2 = new ForkJoinPoolTaskExample(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    public static void main(String[] args) {
        ForkJoinPoolTaskExample myRecursiveTask = new ForkJoinPoolTaskExample(128);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);
        System.out.println("mergedResult = " + mergedResult);
    }
}
