package okhttp.MyHttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

public class Dispathcer {


    private ExecutorService executorService;

    private synchronized ExecutorService executorService(){
        if (executorService == null){
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }

    public void enqueue(RealCall.AsyncCall asyncCall) {
        //执行线程池
        executorService().execute(asyncCall);
    }
}
