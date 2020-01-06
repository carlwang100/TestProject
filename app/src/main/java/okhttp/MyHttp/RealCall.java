package okhttp.MyHttp;


import java.io.IOException;

public class RealCall implements Call{

    private OkHttpClient okHttpClient;
    private Request request;

    public RealCall(OkHttpClient okHttpClient, Request request) {
        this.okHttpClient = okHttpClient;
        this.request = request;
    }

    public static RealCall newCall(OkHttpClient okHttpClient, Request request) {
        RealCall call = new RealCall(okHttpClient, request);
        return call;
    }

    @Override
    public void enqueue(CallBack responseCallback) {
        okHttpClient.dispathcer.enqueue(new RealCall.AsyncCall(responseCallback));
    }

    final class AsyncCall extends NamedRunnable {
        private CallBack callBack;
        public AsyncCall(CallBack responseCallback) {
            callBack = responseCallback;
        }

        @Override
        protected void execute() {
            //处理response
            try {
                callBack.onResponse(RealCall.this, new Response("wangchao"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
