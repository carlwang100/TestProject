package com.example.javatest.fanxing;

public class Generic1<K, V> {
    private K key;
    private V value;


    Generic1(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    //泛型方法
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


}
