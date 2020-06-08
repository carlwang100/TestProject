package eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wangchao.testproject.R;

/**
 * description:
 * author: Darren on 2017/10/31 09:36
 * email: 240336124@qq.com
 * version: 1.0
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_layout);
        findViewById(R.id.test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        EventBus.getDefault().post("text");
                    }
                }.start();
            }
        });
    }
}
