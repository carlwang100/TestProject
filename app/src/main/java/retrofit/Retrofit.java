package retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class Retrofit {

    final Call.Factory callFactory;
    final String baseUrl;

    public Retrofit(Builder builder) {
        this.callFactory = builder.callFactory;
        this.baseUrl = builder.baseUrl;
    }

    public static final class Builder{

        private Call.Factory callFactory;
        private String baseUrl;
        public Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(OkHttpClient client){
            return callFactory(client);
        }

        private Builder callFactory(OkHttpClient client) {
            this.callFactory = client;
            return this;
        }

        public Retrofit build(){
            return new Retrofit(this);
        }

    }

    public <T> T create(final Class<T> service) {
        //前面要对接口做一下校验


        //重点
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (method.getDeclaringClass() == Object.class){
                    return method.invoke(this, args);
                }

                //

                return null;
            }
        });
    }




}
