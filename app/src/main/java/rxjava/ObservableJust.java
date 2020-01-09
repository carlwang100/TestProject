package rxjava;

public class ObservableJust<T> extends Observable<T> {

    private final T item;

    public  ObservableJust(T item) {
        this.item = item;
    }


    @Override
    protected void subscribeActual(Observer<? super T> observer) {
            try {
                observer.onSubscribe();
                observer.onNext(item);
                observer.onComplete();
            }catch (Throwable e){
                observer.onError(e);
            }
    }

}
