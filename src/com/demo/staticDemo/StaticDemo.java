package com.demo.staticDemo;

public class StaticDemo {

    public static void main(String[] args) {
        StaticBean staticBean = new StaticBean("1");
        StaticBean staticBean2 = new StaticBean("2");
        StaticBean staticBean3 = new StaticBean("3");
        StaticBean staticBean4 = new StaticBean("4");
        StaticBean.age = 33;
        System.out.println("" + staticBean+staticBean2+staticBean3+staticBean4);
        StaticBean.SayHello();
    }
}
