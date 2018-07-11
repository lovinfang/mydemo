package com.demo.concurrent.DNdemo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义独占锁
 */
public class SingleLock implements Lock {

    static class Sync extends AbstractQueuedSynchronizer {
        // 独占锁获取
        public boolean tryAcquire(int arg){
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread()); //获取锁的线程
                return true;
            }
            return false;
        }

        //独占锁释放
        public boolean tryRelease(int arg){
            setExclusiveOwnerThread(null);  //当前线程不在占据锁
            setState(0);  //获取锁的时候将状态改为 1  现在改为 0
            return true;
        }

        //判断是否处于占用状态
        public boolean isHeldExclusively(){
            return getState()  == 1;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time)); //纳秒
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
