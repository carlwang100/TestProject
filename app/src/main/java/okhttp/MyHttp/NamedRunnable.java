package okhttp.MyHttp;

public abstract class NamedRunnable implements Runnable {
    @Override
    public void run() {
        execute();
    }

    protected abstract void execute();

}
