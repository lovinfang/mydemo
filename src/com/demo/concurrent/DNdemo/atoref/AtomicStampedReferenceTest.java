package com.demo.concurrent.DNdemo.atoref;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    static AtomicStampedReference<Integer> atomicStampedReference =
            new AtomicStampedReference(0,0);

    public static void main(String[] args) throws InterruptedException {
        final int stamp = atomicStampedReference.getStamp();  //版本戳
        final Integer reference = atomicStampedReference.getReference(); //变量值
        System.out.println("reference:"+reference+"stamp:"+stamp);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * compareAndSet(expectedReference,newReference,expectedStamp,newStamp)
                 * expectedReference:期望值
                 * expectedStamp:期望版本戳
                 * newReference:要修改成的值，如果期望值和期望版本戳相等的话
                 * newStamp:值修改的话，版本戳也要修改成的值
                 *
                 * 内部的实现用到了 CAS
                 */

                System.out.println(Thread.currentThread().getId()+":"+reference + "-"+stamp + "-"
                          +atomicStampedReference.compareAndSet(reference,reference+10,stamp,stamp+1));
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer reference = atomicStampedReference.getReference();
                System.out.println(Thread.currentThread().getId() + ":" + reference + "-" +stamp + "-"
                        + atomicStampedReference.compareAndSet(reference,reference+10,stamp,stamp + 1));
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
}
