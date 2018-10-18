package com.example.wangchao.testproject.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wangchao.testproject.interface2.IBaseView;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseView(bindLayout());
    }

    @SuppressLint("ResourceType")
    private void setBaseView(@LayoutRes int layoutRes) {
        if (layoutRes <= 0) return;
        setContentView(layoutRes);
        initViews();
    }
}
