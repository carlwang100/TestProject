package rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.wangchao.testproject.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.operators.observable.ObservableAll;
import io.reactivex.schedulers.Schedulers;
import sunland.example.wangchao.testproject.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent i = new Intent(context, RxJavaActivity.class);
        context.startActivity(i);
    }
    @Override
    public int bindLayout() {
        return R.layout.activity_rxjava;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        init();
//        testConsumer();
//        testMap();
//        testFlatMap();
//        testZip();
//        testZip2();
//        testFlowable();
        testJust();
    }

    //例子一：创建Observable和Observer
    private void init(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
//                observableEmitter.onComplete();
                observableEmitter.onNext(3);

            }
        }).subscribe(new Observer<Integer>() {
            int i;
            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
                Log.d("RxJavaActivity---", "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2){
                    mDisposable.dispose();
                }
                Log.d("RxJavaActivity---", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d("RxJavaActivity---", "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d("RxJavaActivity---", "onComplete: ");
            }
        });
    }


    //consume只关心onnext事件
    private void testConsumer(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e){
                Log.d("RxJavaActivity---", "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.d("RxJavaActivity---", "accept : " + Thread.currentThread().getName());
                Log.d("RxJavaActivity---", "accept: " + integer);
            }
        });
    }

    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "this is result: " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJavaActivity---", "accept: " + s);
            }
        });
    }


    private void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e)  {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer){
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++){
                    list.add("i am value" + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Log.d("RxJavaActivity---", "accept: " + s);
            }
        });
    }

    private void testZip() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.d("RxJavaActivity---", "onNext: 1");
                e.onNext(2);
                Log.d("RxJavaActivity---", "onNext: 2");
                e.onNext(3);
                Log.d("RxJavaActivity---", "onNext: 3");
            }
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                Log.d("RxJavaActivity---", "onNext: A");
                e.onNext("B");
                Log.d("RxJavaActivity---", "onNext: B");
                e.onNext("C");
                Log.d("RxJavaActivity---", "onNext: C");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer o, String o2)  {
                return o + o2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJavaActivity---", "onNext: " + s);
            }
        });
    }

    private void testZip2() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for(int i = 0;; i++){
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
            }
        }).subscribeOn(AndroidSchedulers.mainThread());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer o, String o2)  {
                return o + o2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("RxJavaActivity---", "onNext: " + s);
            }
        });
    }

    private void testFlowable (){
        Flowable<Integer> upStream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter emitter) throws Exception {
//                Log.d("RxJavaActivity---", "emit 1");
//                emitter.onNext(1);
//                Log.d("RxJavaActivity---", "emit 2");
//                emitter.onNext(2);
//                Log.d("RxJavaActivity---", "emit 3");
//                emitter.onNext(3);
//                Log.d("RxJavaActivity---", "emit complete");
//                emitter.onComplete();
                for (int i = 0; i < 128; i++){ //Flowable 容量是128个事件，
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber<Integer> downStream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d("RxJavaActivity---", "onSubscribe: ");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("RxJavaActivity---", "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("RxJavaActivity---", "onError: " + t);
            }

            @Override
            public void onComplete() {
                Log.d("RxJavaActivity---", "onComplete: ");
            }
        };

        upStream.subscribe(downStream);

    }


    void testJust(){
        Observable.just("wangchao")
                .map(new Function<String, String>() {

                    @Override
                    public String apply(String s) throws Exception {
                        return s + " nihao";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("tag", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("tag", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag", "onComplete: ");
                    }
                });
    }



}
