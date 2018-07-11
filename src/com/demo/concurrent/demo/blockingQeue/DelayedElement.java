package com.demo.concurrent.demo.blockingQeue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement  implements Delayed {

    private String name;

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
