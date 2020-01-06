package com.example.alg_lib.threadlocal;

public class ThreadLocalTest {

    private boolean flag = true;
    private ThreadLocal<Integer> local  = new ThreadLocal<>();

    public void testLocal(){
        local.set(1);
        System.out.println(Thread.currentThread().getName() + "-----" + local.get());
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                local.set(2);
                System.out.println(currentThread().getName() + "-----" + local.get());
            }
        };
        t1.start();

        new Thread("t2"){
            @Override
            public void run() {
                local.set(3);
                System.out.println(currentThread().getName() + "-----" + local.get());
            }
        }.start();
    }
}
