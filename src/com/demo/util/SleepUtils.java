package com.demo.util;

import java.util.concurrent.TimeUnit;

/**
 * 休眠时间工具类
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
