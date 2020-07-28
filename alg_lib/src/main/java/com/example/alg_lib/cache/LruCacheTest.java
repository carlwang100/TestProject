package com.example.alg_lib.cache;

public class LruCacheTest {
    public static void lruTest(){
        LruCache cache = new LruCache(5);
        cache.put("1", "wangchao");
        cache.put("2", "wangchao");
        cache.put("3", "wangchao");
        cache.put("4", "wangchao");
        cache.put("5", "wangchao");
        cache.put("6", "wangchao");
        cache.get("4");
        cache.get("2");
        cache.put("4", "wamgchao");
        cache.printNode();
    }
}
