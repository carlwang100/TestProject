package com.example.wangchao.testproject.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.example.wangchao.testproject.interface2.IBaseView;

public abstract class BaseActivity extends Activity implements IBaseView{

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
