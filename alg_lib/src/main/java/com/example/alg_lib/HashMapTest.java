package com.example.alg_lib;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapTest {

    public void testTraversal() {
//        HashMap<Integer, String> map = new HashMap(1);
//        map.put(7, "");
//        map.put(11, "");
//        map.put(43, "");
//        map.put(59, "");
//        map.put(19, "");
//        map.put(3, "");
//        map.put(35, "");
//
//        System.out.println("遍历结果：");
//        for (Integer key : map.keySet()) {
//            System.out.print(key + " -> ");
//        }
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.get(0);
        map.get(1);
        map.put(4,4);
        map.get(1);
        map.get(2);
        //最近访问的最后输出，这正式lru缓存的策略
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
