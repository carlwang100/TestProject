package rxjava;

import android.util.Log;

public class ObservableMap<T, R> extends Observable<R> {

    final Observable<T> source;
    final Function<T, R> function;

    public ObservableMap(Observable<T> observable, Function<T, R> fun) {
        this.source = observable;
        this.function = fun;
    }


    @Override
    protected void subscribeActual(Observer<? super R> observer) {
        //source 是上游传过来的ObservableJust
        source.subscribe(new MapObserver(observer, function));
    }


    private class MapObserver<T> implements Observer<T> {
        final Observer<R> observer;
        final Function<T, R> function;

        public MapObserver(Observer<R> source, Function<T,R> function) {
            this.observer = source;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }


        @Override
        public void onNext(T t) {
            try {
                R ret = function.apply(t);
                observer.onNext(ret);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

    }
}
