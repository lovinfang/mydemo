package com.demo.concurrent.DNdemo.collect.forkjoin.SunWuKong.Impl;

import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IProcessTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.PanTao;

public class WuKongProcessImpl implements IProcessTaoZi {
    @Override
    public void processTaoZi(PanTao taoZi) {
        //看看桃子，放到口袋里
        inBag();
    }

    private void inBag(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
