package com.demo.staticDemo;

/**
 *  执行顺序:静态代码块—非静态代码块—构造方法
 *  静态代码块只执行一次
 */
public class StaticAndNonStaticDemo {

    public StaticAndNonStaticDemo(){
        System.out.print("默认构造方法！");
    }

    //非静态代码块
    {
        System.out.print("非静态代码块！");
    }

    //静态代码块
    static{
        System.out.print("静态代码块！");
    }

    public static void test(){
        System.out.println("静态方法中的内容！");
        {
            System.out.print("静态方法中的代码块！");
        }
    }

    public static void main(String[] args) {
        StaticAndNonStaticDemo staticAndNonStaticDemo = new StaticAndNonStaticDemo();
        System.out.println();
        System.out.println("----------------");
        StaticAndNonStaticDemo staticAndNonStaticDemo2 = new StaticAndNonStaticDemo();
        System.out.println();
        System.out.println("----------------");
        StaticAndNonStaticDemo.test();
    }
}
