package com.example.javatest;

import com.example.javatest.fanxing.GenericTest;
import com.example.javatest.observer.ObserverTest;
import com.example.javatest.proxy.ProxyTest;

public class MyClass {
    public static void main(String[] args){

        ProxyTest proxyTest = new ProxyTest();
//        proxyTest.testStaticProxy();
//        proxyTest.testDynamicProxy();
//        GenericTest.testGenetic();
        ObserverTest observerTest = new ObserverTest();
        observerTest.test();

    }
}
