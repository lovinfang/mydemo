package com.demo.concurrent.DNdemo.collect.forkjoin.BaJie.impl;

import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IProcessTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.PanTao;

public class BaJieProcessImpl implements IProcessTaoZi {
    @Override
    public void processTaoZi(PanTao taoZi) {
        //看看桃子，一口吃了
        eat();
    }

    //看看桃子，一口吃了
    private void eat(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
