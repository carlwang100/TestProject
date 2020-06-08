package com.example.alg_lib.threadlocal;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable {

    private int num = 1000;
    ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        //普通的同步
//            while(true){
////                try {
////                    //sleep 暂停当前线程但是不释放锁资源
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                //this 是指当前线程 这里有两个线程 随然加了锁 但是不存在竞争关系 因为锁的对象不一致，两个线程都有可能运行，但是每次绝对只有一个在运行，这就是sync锁的作用
//                // 如果锁的是.class 就存在着锁的竞争了，因为他们是用的同一把锁
//                synchronized (this){
//                    if (num > 0){
//                        System.out.println(Thread.currentThread().getName() + "..." + num--);
//                    }
//                }
//            }


//        while (true) {
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
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
            //这里的this是对象自己，这里具体指的是thread对象自己

            //notify
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
