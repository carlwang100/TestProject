package com.example.javatest.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    /**
     * 静态代理 这里的代理对象sale是直接new 出来，并且是将client以参数的形式传递进去的，耦合在一起了
     * */
    public void testStaticProxy(){
        IBank client = new Client();
        IBank sale = new Sale(client);
        sale.applyBank();
    }

    /**
     * 动态代理：在编译的时候动态的生成代理对象，使用的反射机制;
     * f
     * */
    public void testDynamicProxy(){
        //原始类
        IBank client = new Client();
        Class<?>[] clientClazz = client.getClass().getInterfaces();
        //注意三个参数
        //生成代理类的一个实例sale
        IBank sale = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(), clientClazz,  new DynamicBankProxy(client));
        //执行方法的时候才会触发代理类的invoke方法，
        sale.applyBank();
        sale.zhuanqian();
    }
}
