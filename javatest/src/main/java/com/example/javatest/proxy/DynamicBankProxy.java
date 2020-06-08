package com.example.javatest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicBankProxy implements InvocationHandler {

    private Object proxy;
    public DynamicBankProxy(Object object){
        this.proxy = object;
    }
    //这里的method是指IBank 的applyBank  只有
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("检查信息");
        Object ret = method.invoke(proxy, args);
        System.out.println("办卡完毕");
        return null;
    }
}
