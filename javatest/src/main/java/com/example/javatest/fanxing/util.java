package com.example.javatest.fanxing;

public class util {

    public static <K, V> boolean compare(Generic1<K, V> g1, Generic1<K, V> g2){
        return g1.getKey().equals(g2.getKey()) && g2.getKey().equals(g2.getKey());
    }
}
