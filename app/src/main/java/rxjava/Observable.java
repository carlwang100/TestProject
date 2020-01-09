package rxjava;



public abstract class Observable<T> implements ObservableSource<T>{


    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust(item));
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        subscribeActual(observer);
    }


    public  <R> Observable<R> map(Function< T,  R> mapper){
        return onAssembly(new ObservableMap<>(this, mapper));
    }

    private static <T> Observable<T> onAssembly(Observable<T> observableJust) {
        return observableJust;
    }

    protected abstract void subscribeActual(Observer<? super T> observer);
}
