package com.example.javatest.proxy;

public class Sale implements IBank{
    private IBank iBank;

    public Sale(IBank bank){
        iBank = bank;
    }

    @Override
    public void applyBank() {
        System.out.println("检查信息");
        iBank.applyBank();
        System.out.println("办卡结束");
    }

    @Override
    public void zhuanqian() {

    }
}
