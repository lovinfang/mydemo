package com.demo.deadlock.bank.service;


import com.demo.deadlock.bank.Account;

/**
 * 创建日期：2017/12/14
 * 创建时间: 20:31
 * 根据hashcode值进行锁
 */
public class SafeTransfer implements ITransfer {

    private static Object tieLock = new Object();

    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {

        //我们不仅仅只能用System.identityHashCode(from);  如果Account有主键id 也是可以使用的
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash<toHash){
            synchronized (from){
                System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                Thread.sleep(100);
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else if(toHash<fromHash){
            synchronized (to){
                System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                Thread.sleep(100);
                synchronized (from){
                    System.out.println(Thread.currentThread().getName()
                            +" get "+from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else{
//            System.identityHashCode(from);  造成相等的情况是千万分之一，但是也要考虑进去
            synchronized (tieLock){
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                    Thread.sleep(100);
                    synchronized (from){
                        System.out.println(Thread.currentThread().getName()
                                +" get "+to.getName());
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}
