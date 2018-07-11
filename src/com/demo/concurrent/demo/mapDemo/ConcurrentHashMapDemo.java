package com.demo.concurrent.demo.mapDemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentMap concurrentMap = new ConcurrentHashMap();
        concurrentMap.put("Key","Value");
        Object obj = concurrentMap.get("Key");
        System.out.println(obj.toString());
    }
}
