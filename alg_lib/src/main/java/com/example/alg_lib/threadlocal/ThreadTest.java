package com.example.alg_lib.threadlocal;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable {

    private int num = 20;
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
//            while(true){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (this){
//                    if (num > 0){
//                        System.out.println(Thread.currentThread().getName() + "..." + num--);
//                    }
//
//                }
//            }


//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            if (Thread.currentThread().getName().equals("Thread-0")) {
////                try {
////                    Thread.currentThread().join();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }
//            lock.lock();
//            if (num > 0) {
//                System.out.println(Thread.currentThread().getName() + "..." + num--);
//            } else {
//                break;
//            }
//            lock.unlock();
//
//        }

//        一个打印奇数一个打印偶数
        while(num > 0){
            synchronized (this){
                notify();
                System.out.println(Thread.currentThread().getName() + "..." + num--);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }


    }
}
