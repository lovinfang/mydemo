package com.demo.math;

import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void test(){
        /**
         * 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断。 具体原理和浮点数的编码方式有关
         */
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false
    }

    public static void test2(){
        /**
         * 具有基本数学知识的我们很清楚的知道输出并不是我们想要的结果（精度丢失），我们如何解决这个问题呢？一种很常用的方法是：
         * 使用使用 BigDecimal 来定义浮点数的值，再进行浮点数的运算操作。
         */
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);// 0.1
        BigDecimal y = b.subtract(c);// 0.1
        System.out.println(x.equals(y));// true
    }

    /**
     * BigDecimal 的大小比较
     */
    public static void test3(){
        /**
         * a.compareTo(b) : 返回 -1 表示小于，0 表示 等于， 1表示 大于。
         */
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        System.out.println(a.compareTo(b));// 1
    }

    /**
     * 通过 setScale方法设置保留几位小数以及保留规则
     */
    public static void test4(){
        BigDecimal m = new BigDecimal("1.255433");
        BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(n);// 1.255
    }

    public static void main(String[] args) {
        System.out.println("------test()------");
        BigDecimalDemo.test();
        System.out.println("------test2()------");
        BigDecimalDemo.test2();
        System.out.println("------test3()------");
        BigDecimalDemo.test3();
        System.out.println("------test4()------");
        BigDecimalDemo.test4();
    }
}
