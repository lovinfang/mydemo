package com.demo.deadlock.bank.service;


import com.demo.deadlock.bank.Account;

/**
 * 创建日期：2017/08/30
 * 创建时间: 15:35
 */
public interface ITransfer {

    void transfer(Account from, Account to, int amount) throws InterruptedException;
}
