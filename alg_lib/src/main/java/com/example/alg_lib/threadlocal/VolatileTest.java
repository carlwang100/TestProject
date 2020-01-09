package com.example.alg_lib.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    private AtomicInteger increace = new AtomicInteger();

    public volatile int race = 0;

    public void testVolitale(){
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                        for (int i = 0; i < 5; i++) {
                            increase();

                    }
                }
            });
            threads[i].start();
        }
//        while (Thread.activeCount() > 1)
//            Thread.yield();


    }

    private void increase() {
        increace.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "-->>" + increace);
    }


    //加锁方式
//    private synchronized void increase() {
//        race++;
//        System.out.println(Thread.currentThread().getName() + "-->>" + race);
//    }


}
