package com.demo.concurrent.DNdemo.lock.rwlock.impl;

import com.demo.concurrent.DNdemo.lock.rwlock.GoodsVo;
import com.demo.concurrent.DNdemo.lock.rwlock.IGoodsNum;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwNumImpl implements IGoodsNum {

    private GoodsVo goods;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final  Lock w = lock.writeLock();

    public RwNumImpl(GoodsVo goods) {
        this.goods = goods;
    }

    /**
     * 模拟数据库读操作
     * @return
     */
    @Override
    public GoodsVo getGoodsNumber() {
        r.lock();
        try{
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.goods;
        }finally{
            r.unlock();
        }
    }

    /**
     * 模拟数据库写操作
     * @param changeNumber
     */
    @Override
    public void setGoodsNumber(int changeNumber) {
        w.lock();
        try{
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.goods.setGoodsVoNumber(changeNumber);
        }finally{
            w.unlock();
        }
    }
}
