package com.example.javatest.proxy;

public class Client implements IBank, IBank2{

    @Override
    public void applyBank() {
        System.out.println("办卡");
    }

    @Override
    public void zhuanqian() {

    }

    @Override
    public void applyBank2() {

    }
}
