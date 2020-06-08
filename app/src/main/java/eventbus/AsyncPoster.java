package eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AsyncPoster implements Runnable {

    Subscription subscription;
    Object event;
    private static final ExecutorService executorService  = Executors.newCachedThreadPool();

    AsyncPoster(Subscription subscription, Object event){
        this.subscription = subscription;
        this.event = event;
    }

    public static void enqueue(Subscription subscription, Object event){
        AsyncPoster asyncPoster = new AsyncPoster(subscription,event);
        executorService.execute(asyncPoster);
    }

    @Override
    public void run() {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
