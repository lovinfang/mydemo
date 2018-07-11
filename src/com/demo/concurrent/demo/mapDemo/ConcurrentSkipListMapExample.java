package com.demo.concurrent.demo.mapDemo;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {

    public static void main(String[] args) {
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();

        map.put("1","one");
        map.put("2","one");
        map.put("3","one");

        //headMap 将指向一个只含有键 "1" 的 ConcurrentNavigableMap，因为只有这一个键小于 "2"
        ConcurrentNavigableMap headMap = map.headMap("2");
        //tailMap 将拥有键 "2" 和 "3"，因为它们不小于给定键 "2"。
        ConcurrentNavigableMap tailMap = map.tailMap("2");
        //返回的 submap 只包含键 "2"，因为只有它满足不小于 "2"，比 "3" 小。
        ConcurrentNavigableMap subMap = map.subMap("2", "3");
    }
}
