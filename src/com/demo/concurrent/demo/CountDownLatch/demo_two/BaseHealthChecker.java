package com.demo.concurrent.demo.CountDownLatch.demo_two;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable{

    private CountDownLatch _latch;
    private String _serviceName;
    private boolean _serviceUp;

    public BaseHealthChecker(String serviceName,CountDownLatch latch) {
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            _serviceUp = true;
        } catch (Exception e) {
            e.printStackTrace();
            _serviceUp = false;
        } finally{
            if(_latch != null){
                _latch.countDown();
            }
        }
    }

    public abstract void verifyService();

    public String getServiceName(){
        return _serviceName;
    }

    public boolean isServiceUp(){
        return _serviceUp;
    }
}
