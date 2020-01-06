package okhttp.MyHttp;

import java.io.IOException;


public interface  CallBack {

     void onFailure(Call call, IOException e);


    void onResponse(Call call, Response response) throws IOException;
}