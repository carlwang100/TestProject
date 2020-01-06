package com.example.javatest.proxy;

public class ProxyTest {

    public void testStaticProxy(){
        IBank client = new Client();
        IBank sale = new Sale(client);
        sale.applyBank();
    }
}
