package eventbus;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    static volatile EventBus instance = null;

    //第一个容器
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    //第二个容器 用于解注册
    private final Map<Object, List<Class<?>>> typesBySubscriber;


    EventBus(){
        subscriptionsByEventType = new HashMap<>();
        typesBySubscriber = new HashMap<>();
    }

    public static EventBus getDefault(){
        if (instance == null){
            //注意这里千万不要写成EventBus.this
            synchronized (EventBus.class){
                if (instance == null){
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }


    public void register(Object subscriber) {
        List<SubscriberMethod> subcriberMethods = new ArrayList<>();
        //获取类的实例
        Class<?> subscriberClass = subscriber.getClass();
        Method[] methods = subscriberClass.getMethods();
        //1.找订阅中合适的方法
        for (Method method : methods) {
            //解析带有Subcriber注解的方法
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            Class<?>[] paramTypes =  method.getParameterTypes();
            if (subscribe != null){
                subcriberMethods.add(new SubscriberMethod(method, paramTypes[0], subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
            }
        }

        // 2. 按照规则存放到 subscriptionsByEventType 里面去
        for (SubscriberMethod subcriberMethod : subcriberMethods) {
            subscribe(subscriber, subcriberMethod);
        }

    }

    // 2. 按照规则存放到 subscriptionsByEventType 里面去
    private void subscribe(Object subscriber, SubscriberMethod subcriberMethod) {
        Class<?> eventType = subcriberMethod.eventType; //这里是String ，eventType是方法的第一个参数
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        //首次肯定是空的 以参数的class为key去存放
        if (subscriptions == null){
            subscriptions  = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType, subscriptions);
        }
        Subscription subscription = new Subscription(subscriber, subcriberMethod); //subscriber是EventBusActivity
        subscriptions.add(subscription);


        //解注册的map容器使用的key是类对象 一般是一个类销毁的时候需要解注册，查找类里面的所以subscribe的方法，然后remove掉
        List<Class<?>> eventTypes = typesBySubscriber.get(subscriber);
        if (eventTypes == null){
            eventTypes = new ArrayList<>();
            typesBySubscriber.put(subscriber, eventTypes);
        }

        //用于解注册
        if (!eventTypes.contains(eventType)){
            eventTypes.add(eventType);
        }
    }



    public void unregister(Object subscriber) {
        List<Class<?>> subscribedTypes = typesBySubscriber.get(subscriber);
        if (subscribedTypes != null){
            for (Class<?> subscribedType : subscribedTypes) {
                unsubscribeByEventType(subscriber, subscribedType);
            }
            typesBySubscriber.remove(subscriber);
        }
    }

    private void unsubscribeByEventType(Object subscriber, Class<?> subscribedType) {
        List<Subscription> subscriptions = subscriptionsByEventType.get(subscribedType);
        if (subscriptions != null){
            int size = subscriptions.size();
            //边循环边remove要注意，iterator的用法
            for (int i = 0; i < size; i++) {
                Subscription subscription = subscriptions.get(i);
                if (subscription.subscriber == subscriber){
                    subscriptions.remove(i);
                    i--;
                    size--;
                }
            }
        }
    }

    //遍历 subscriptionsByEventType，找到符合的方法调用方法的 method.invoke() 执行。
    public void post(Object event) {
        Class<?> eventType = event.getClass(); //String.class
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null && !subscriptions.isEmpty()){
            for (Subscription subscription : subscriptions) {
                excuteMothed(subscription, event);
            }
        }
    }

    private void excuteMothed(final Subscription subscription, final Object event) {
        boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();//判断是否在主线程
        switch (subscription.subscriberMethod.threadMode){
            case MAIN:
                if (isMainThread){
                    invokeSubcriber(subscription, event);
                }else {
                    //子线程中初始化Handler要getMainLooper（）拿到主线程的looper
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            invokeSubcriber(subscription, event);
                        }
                    });
                }
                break;
            case ASYNC:
                AsyncPoster.enqueue(subscription, event);
                break;
            case POSTING:
                invokeSubcriber(subscription, event);
                break;
            case BACKGROUND:
                if (!isMainThread){
                    invokeSubcriber(subscription, event);
                }else {
                    AsyncPoster.enqueue(subscription, event);
                }
                break;
        }
    }

    private void invokeSubcriber(Subscription subscription, Object event) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
