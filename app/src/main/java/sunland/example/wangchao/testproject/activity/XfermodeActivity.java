package sunland.example.wangchao.testproject.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.wangchao.testproject.R;

public class XfermodeActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent i = new Intent(context, XfermodeActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.xfermode_layout;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }
}
