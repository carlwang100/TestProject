package com.example.alg_lib.threadlocal;

public class VolatileTest {

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

    public volatile int race = 0;

    private synchronized void increase() {
            race++;
            System.out.println(Thread.currentThread().getName() + "-->>" + race);
        }
}
