package com.demo.threadDemo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 普通程序获取线程的demo
 */
public class ThreadMXBeanDemo {

    public static void main(String[] args) {
        //java虚拟机的线程管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程信息的方法
        ThreadInfo threadInfos[] = threadMXBean.dumpAllThreads(false,false);

        for(ThreadInfo t : threadInfos){
            System.out.println(t.getThreadId()+":"+t.getThreadName());
        }
    }
}
