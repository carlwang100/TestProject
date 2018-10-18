package com.example.wangchao.testproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangchao.testproject.R;

public class CustomViewActivity extends BaseActivity {
    LinearLayout linearLayout;

    public static void startActivity(Context context){
        Intent i = new Intent(context, CustomViewActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.customview_layout;
    }

    @Override
    public void initViews() {
        linearLayout = findViewById(R.id.ll_content);
        initImageViews();
    }

    @Override
    public void onClick(View v) {

    }

    private void initImageViews() {
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.adpage_switch_to_horizontal_list);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 30;
            linearLayout.addView(imageView, params);
        }
    }

}
