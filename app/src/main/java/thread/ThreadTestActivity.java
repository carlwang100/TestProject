package thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wangchao.testproject.R;
import sunland.example.wangchao.testproject.activity.BaseActivity;


public class ThreadTestActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ThreadTestActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.thread_layout;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExecutorsDemo executors = new ExecutorsDemo();
//        executors.testNewFixedThreadPool();
        executors.textNewSingleThreadExecutors();
    }

}
