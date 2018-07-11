package com.demo.concurrent.DNdemo.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArray {
    static int[] value = new int[]{1,2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value); //将value数组的值复制一份到ai中

    public static void main(String[] args) {
        ai.getAndSet(0,3); //将第0位值修改成3
        System.out.println(ai.get(0));
        System.out.println(value[0]); //原来的数组是不会进行改变的
    }
}

