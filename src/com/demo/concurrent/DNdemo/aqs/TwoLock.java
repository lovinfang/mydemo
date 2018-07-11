package com.demo.concurrent.DNdemo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 允许两个线程进行操作
 */
public class TwoLock implements Lock {
    /**
     * 实现自己的同步操作
     */
    static class Sync extends AbstractQueuedSynchronizer {
        /**
         *
         * @param count  初始化 确定锁有几个资源可以拿
         */
        Sync(int count){
            setState(count); //确定有几个锁可以让后面的线程去拿
        }

        //  共享锁获取
        public int tryAcquireShared  (int arg){
            for(;;){
                int current = getState(); //查看当前状态，确认有几个锁可以拿
                int newCount = current - arg; //扣减之后的数量  arg:被拿的数量  newCount:剩余的数量
                if(newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        //共享锁释放
        public boolean tryReleaseShared  (int arg){
            for(;;){
                int current = getState();
                int newCount = current + arg;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }

        Condition newCondition(){
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
