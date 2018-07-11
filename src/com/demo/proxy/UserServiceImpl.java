package com.demo.proxy;

/**
 * 目标对象
 */
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("--------------------add---------------");
    }
}
