package okhttp.MyHttp;

import okhttp3.Callback;

public interface Call {
    void enqueue(CallBack responseCallback);
}
