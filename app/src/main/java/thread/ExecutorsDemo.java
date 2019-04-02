package thread;

import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 *  1.）new Thread()的缺点
 *
 * 每次new Thread()耗费性能
 * 调用new Thread()创建的线程缺乏管理，被称为野线程，而且可以无限制创建，之间相互竞争，会导致过多占用系统资源导致系统瘫痪。
 * 不利于扩展，比如如定时执行、定期执行、线程中断
 *     2.）采用线程池的优点
 *
 * 重用存在的线程，减少对象创建、消亡的开销，性能佳
 * 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞
 * 提供定时执行、定期执行、单线程、并发数控制等功能
 * */

public class ExecutorsDemo {

    public void testNewFixedThreadPool(){
        ExecutorService executors = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d("wangchao05", "run: " + Thread.currentThread().getName());
                }
            };
            executors.execute(runnable);
        }
    }

    public void textNewSingleThreadExecutors(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d("wangchao05", "run: " + Thread.currentThread().getName());
                }
            };
            executorService.execute(runnable);
        }
    }

}
