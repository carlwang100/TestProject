package okhttp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wangchao.testproject.R;
import com.example.wangchao.testproject.activity.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class OkhttpActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent i = new Intent(context, OkhttpActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.okhttp_layout;
    }

    @Override
    public void initViews() {
       findViewById(R.id.http_get).setOnClickListener(this);
       findViewById(R.id.http_post).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.http_get:
                getRequest();
                break;
            case R.id.http_post:
//                postFile();
                break;
        }
    }

    public static final MediaType TYPE = MediaType.parse("text/x-markdown; charset=utf-8");


    /**
     * post提交文件
     * */
    private void postFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("README.md");
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(TYPE, file))
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * post HTML标签格式
     * */

    private void postHtmlTag(){
        RequestBody formBody = new FormBody.Builder()
                .add("name", "wangchao")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();
    }



    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");


    /**
     * post分块请求,复杂请求中使用
     * */
    private void postMultipart(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", "wangchao")
                .addFormDataPart("image", "logo", RequestBody.create(MEDIA_TYPE_PNG,
                        new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "")
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();
    }


    //
    private void postRequest() {

    }

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();


    /**
     * websocket
     * */

    void webSocketRequest(){
        String wsUrl = "ws://" + "hostname" + ":" + "port:" + "/";
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @javax.annotation.Nullable Response response) {
                super.onFailure(webSocket, t, response);
            }
        });
    }

    private void getRequest(){
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.wooyun.org")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()){
                        final Response finalResponse = response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Log.i("wangchao","打印GET响应的数据：" + finalResponse.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }else {
                        final Response finalResponse1 = response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("wangchao", "run: error");
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
