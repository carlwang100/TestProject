package com.example.wangchao.testproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.wangchao.testproject.activity.BaseActivity;

public class PathViewActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent i = new Intent(context, PathViewActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_path_view;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }
}
