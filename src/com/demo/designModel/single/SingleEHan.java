package com.demo.designModel.single;

/**
 * 创建日期：2017/12/14
 * 创建时间: 21:59
 * 饿汉式单例
 */
public class SingleEHan {
    public static SingleEHan singleEHan = new SingleEHan();
    private SingleEHan(){};

}
