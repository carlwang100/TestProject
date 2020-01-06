package com.example.javatest.proxy;

public class Client implements IBank{

    @Override
    public void applyBank() {
        System.out.println("办卡");
    }
}
