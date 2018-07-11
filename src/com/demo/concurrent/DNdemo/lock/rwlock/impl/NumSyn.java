package com.demo.concurrent.DNdemo.lock.rwlock.impl;

import com.demo.concurrent.DNdemo.lock.rwlock.GoodsVo;
import com.demo.concurrent.DNdemo.lock.rwlock.IGoodsNum;

public class NumSyn implements IGoodsNum {

    private GoodsVo goods;

    public NumSyn(GoodsVo goods) {
        this.goods = goods;
    }

    /**
     * 模拟数据库读操作
     * 读库为什么要加synchronized
     */
    @Override
    public synchronized GoodsVo getGoodsNumber() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.goods;
    }

    /**
     * 模拟数据库写操作
     * @param changeNumber
     */
    @Override
    public synchronized void setGoodsNumber(int changeNumber) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.goods.setGoodsVoNumber(changeNumber);
    }
}
