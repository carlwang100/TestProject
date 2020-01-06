package okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 缓存30s的缓存拦截器
 * */
public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response != null){
            response = response.newBuilder()
                    .removeHeader("Cache-Control")
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "max-age=" + 30)
                    .build();
        }
        return response;
    }

}
