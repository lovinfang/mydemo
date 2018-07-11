package com.demo.concurrent.DNdemo.collect.forkjoin.BaJie.impl;

import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IPickTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IProcessTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.Color;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.PanTao;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.Size;

public class BaJiePickImpl implements IPickTaoZi {
    private IProcessTaoZi processTaoZi;

    public BaJiePickImpl(IProcessTaoZi processTaoZi) {
        this.processTaoZi = processTaoZi;
    }

    @Override
    public boolean pick(PanTao[] src, int index) {
        if(src[index].getColor()== Color.RED&&
                src[index].getSize()== Size.BIG){
            processTaoZi.processTaoZi(src[index]);
            return true;
        }else{
            return false;
        }
    }
}
