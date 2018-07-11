package com.demo.concurrent.demo.forkJoinPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolActionExample extends RecursiveAction {

    private long workLoad = 0;

    public ForkJoinPoolActionExample(long workLoad) {
        this.workLoad = workLoad;
    }
    @Override
    protected void compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);
            List<ForkJoinPoolActionExample> subtasks = new ArrayList<ForkJoinPoolActionExample>();
            subtasks.addAll(createSubtasks());
            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }
        }else{
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<ForkJoinPoolActionExample> createSubtasks() {
        List<ForkJoinPoolActionExample> subtasks = new ArrayList<ForkJoinPoolActionExample>();
        ForkJoinPoolActionExample subtask1 = new ForkJoinPoolActionExample(this.workLoad / 2);
        ForkJoinPoolActionExample subtask2 = new ForkJoinPoolActionExample(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }

    public static void main(String[] args) {
        ForkJoinPoolActionExample myRecursiveAction = new ForkJoinPoolActionExample(24);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(myRecursiveAction);
    }
}
