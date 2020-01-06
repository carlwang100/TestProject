package okhttp.MyHttp;


import java.util.HashMap;
import java.util.Map;

public class Request {
    final String url;
    final Method method;

    public Request(Builder build) {
        this.url = build.url;
        this.method = build.method;
    }


    public static class Builder {
        private String url;
        private Method method;

        public Builder(){
            method = Method.GET;

        }

        private Builder get(){
            method = Method.GET;
            return this;
        }

        private Builder post(RequestBody requestBody){
            method = Method.POST;
            return this;
        }

        private Builder url(String url){
            this.url = url;
            return this;
        }



        public Request build() {
            return new Request(this);
        }


    }
}
