package com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi;

import com.demo.concurrent.DNdemo.collect.forkjoin.vo.PanTao;

/**
 * 摘桃子的接口
 */
public interface IPickTaoZi {
    boolean pick(PanTao[] src, int index);
}
