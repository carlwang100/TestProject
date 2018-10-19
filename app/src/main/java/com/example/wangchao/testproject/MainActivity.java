package com.example.wangchao.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangchao.testproject.activity.BaseActivity;
import com.example.wangchao.testproject.activity.CustomViewActivity;
import com.example.wangchao.testproject.activity.WaterImgActivity;
import com.example.wangchao.testproject.activity.XfermodeActivity;

import java.text.DecimalFormat;

import okhttp.OkhttpActivity;
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
        Log.d("wangchao", "" + r);
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
        }
    }
}
