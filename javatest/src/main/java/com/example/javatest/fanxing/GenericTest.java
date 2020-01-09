package com.example.javatest.fanxing;

public class GenericTest {

    public static void testGenetic(){
        Generic1<Integer, String> g1 = new Generic1<>(1, "apple");
        Generic1<Integer, String> g2 = new Generic1<>(2, "banana");
        System.out.println(util.compare(g1, g2));;
    }

}
