package com.demo.designModel.single;

/**
 * 创建日期：2017/12/14
 * 创建时间: 22:01
 * 懒汉式单例-双重检查
 */
public class SingleDcl {

    /**
     * 如果不加 volatile 则也不是线程安全的，因为第一个线程在拿到锁的时候正在创建类对象
     * 但是并没有创建完，第二个线程发现这个类对象不为空，但是还没创建完会直接返回，所以不是线程安全的
     * 加上volatile则是一个线程安全的，目前不推荐使用这种方式
     */
    private volatile static SingleDcl single;
    private SingleDcl(){}

    public static SingleDcl getInstance(){
        if(null==single){
            synchronized (SingleDcl.class){
                if(single==null){
                    single = new SingleDcl();
                }
            }
        }
        return single;
    }


}
