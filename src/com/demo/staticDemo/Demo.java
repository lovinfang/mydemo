package com.demo.staticDemo;

public class Demo {

    static {
        i = 3;
//        System.out.println(i);   //静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问
                                   // 如果private static int i; 在之前定义，那么是可以访问的，不会报错
    }

    private static int i;
}

