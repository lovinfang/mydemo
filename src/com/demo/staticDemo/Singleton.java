package com.demo.staticDemo;

/**
 * 静态内部类与非静态内部类之间存在一个最大的区别，我们知道非静态内部类在编译完成之后会隐含地保存着一个引用，
 * 该引用是指向创建它的外围类，但是静态内部类却没有。没有这个引用就意味着：
 *
 * 它的创建是不需要依赖外围类的创建。
 * 它不能使用任何外围类的非static成员变量和方法。
 *
 *  静态内部类实现单例模式
 *  当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。只有当调用 getUniqueInstance()
 *  方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例，
 *  并且 JVM 能确保 INSTANCE 只被实例化一次。
 */
public class Singleton {

    // 声明为private 避免调用默认构造方法创建对象
    private Singleton(){

    }

    // 声明为private 表面静态内部该类只能在该 Singleton 类中被访问
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance(){
        return SingletonHolder.INSTANCE;
    }

}
