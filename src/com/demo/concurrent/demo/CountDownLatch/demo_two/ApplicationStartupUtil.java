package com.demo.concurrent.demo.CountDownLatch.demo_two;

import com.demo.concurrent.demo.CountDownLatch.demo_two.impl.CacheHealthChecker;
import com.demo.concurrent.demo.CountDownLatch.demo_two.impl.DatabaseHealthChecker;
import com.demo.concurrent.demo.CountDownLatch.demo_two.impl.NetworkHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {

    private static List<BaseHealthChecker> _services;
    private static CountDownLatch _latch;

    private ApplicationStartupUtil(){}

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance(){
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws InterruptedException {
        _latch = new CountDownLatch(3);
        _services = new ArrayList<BaseHealthChecker>();

        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));
        _services.add(new CacheHealthChecker(_latch));

        Executor executor = Executors.newFixedThreadPool(_services.size());

        for(BaseHealthChecker base : _services){
            executor.execute(base);
        }

        _latch.await();

        for(BaseHealthChecker base : _services){
            if(!base.isServiceUp()){
                return false;
            }
        }

        return true;
    }
}
