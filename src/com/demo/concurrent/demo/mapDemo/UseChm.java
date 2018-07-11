package com.demo.concurrent.demo.mapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UseChm {

    Map<String,String> hashmap = new HashMap<String,String>();
    ConcurrentMap<String,String> chm = new ConcurrentHashMap<>();

    public String putIfAbsent(String key,String value){
        synchronized (hashmap){
            if(hashmap.get(key) != null){
                return hashmap.put(key,value);
            }else{
                return hashmap.get(key);
            }
        }
    }

    public String useChm(String key,String value){
        return chm.putIfAbsent(key,value);
    }
}

