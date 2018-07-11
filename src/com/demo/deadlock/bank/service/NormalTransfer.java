package com.demo.deadlock.bank.service;


import com.demo.deadlock.bank.Account;

/**
 * 创建日期：2017/12/14
 * 创建时间: 20:25
 * 平常转账--容易造成死锁
 */
public class NormalTransfer implements ITransfer{
    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {
        synchronized (from){
            System.out.println(Thread.currentThread().getName()+" get "+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                        +" get "+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
