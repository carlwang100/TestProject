package okhttp.MyHttp;


public class OkHttpClient {

    public Dispathcer dispathcer;

    private OkHttpClient(){
        new Builder();
    }

    public OkHttpClient(Builder build) {
        this.dispathcer = build.dispathcer;
    }

    public Call newCall(Request request) {
        return RealCall.newCall(this, request);
    }


    public static class Builder {
        private Dispathcer dispathcer;

        public Builder(){
            this.dispathcer = new Dispathcer();
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }


    }
}
