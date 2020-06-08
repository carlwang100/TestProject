package com.example.alg_lib.threadlocal;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    CountDownLatch countDownLatch = new CountDownLatch(3);

    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                countDownLatch.countDown();
                System.out.println(2);
                countDownLatch.countDown();
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
