package com.demo.concurrent.DNdemo.semaphpore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphporeCase<T> {

    private final Semaphore items;//有多少元素可拿
    private final Semaphore space;//有多少空位可放元素
    private List queue = new LinkedList<>();

    public SemaphporeCase(int itemCounts){
        this.items = new Semaphore(0);
        this.space = new Semaphore(itemCounts);
    }

    //放入数据
    public void put(T x) throws InterruptedException {
        space.acquire();//拿空位的许可，没有空位线程会在这个方法上阻塞
        synchronized (queue){
            queue.add(x);
        }
        items.release();//有元素了，可以释放一个拿元素的许可
    }

    //取数据
    public T take() throws InterruptedException {
        items.acquire();//拿元素的许可，没有元素线程会在这个方法上阻塞
        T t;
        synchronized (queue){
            t = (T)queue.remove(0);
        }
        space.release();//有空位了，可以释放一个存在空位的许可
        return t;
    }
}
