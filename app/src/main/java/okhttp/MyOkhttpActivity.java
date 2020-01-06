package okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.wangchao.testproject.R;

import java.io.IOException;

import okhttp.MyHttp.Call;
import okhttp.MyHttp.CallBack;
import okhttp.MyHttp.OkHttpClient;
import okhttp.MyHttp.Request;
import okhttp.MyHttp.Response;

public class MyOkhttpActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_okhttp_layout);
        findViewById(R.id.http_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }

    private void init() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        final Request request  = new Request.Builder().build();

        client.newCall(request).enqueue(new CallBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG", "onResponse: " + response.name);
            }
        });

    }


}
