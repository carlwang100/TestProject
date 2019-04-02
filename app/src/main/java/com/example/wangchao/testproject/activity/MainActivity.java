package com.example.wangchao.testproject.activity;

import android.os.Bundle;
import android.view.View;

import com.example.wangchao.testproject.PathViewActivity;
import com.example.wangchao.testproject.R;

import java.text.DecimalFormat;

import okhttp.OkhttpActivity;
import thread.ThreadTestActivity;
import widget.RoundImageActivity;

public class MainActivity extends BaseActivity {

    private double x = 2485;
    double y = 9895;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DecimalFormat df=new DecimalFormat(".##");
        double result = x / y;
        int r = (int) (Double.valueOf(df.format(result)) * 100);
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        findViewById(R.id.widget).setOnClickListener(this);
        findViewById(R.id.okhttp).setOnClickListener(this);
        findViewById(R.id.waterimg).setOnClickListener(this);
        findViewById(R.id.path_btn).setOnClickListener(this);
        findViewById(R.id.xfo__btn).setOnClickListener(this);
        findViewById(R.id.round_img_btn).setOnClickListener(this);
        findViewById(R.id.thread_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.widget:
                CustomViewActivity.startActivity(MainActivity.this);
                break;
            case R.id.okhttp:
                OkhttpActivity.startActivity(this);
                break;
            case R.id.waterimg:
                WaterImgActivity.startActivity(this);
                break;
            case R.id.path_btn:
                PathViewActivity.startActivity(this);
                break;
            case R.id.xfo__btn:
                XfermodeActivity.startActivity(this);
                break;
            case R.id.round_img_btn:
                RoundImageActivity.startActivity(this);
                break;
            case R.id.thread_btn:
                ThreadTestActivity.startActivity(this);
                break;
        }
    }
}
