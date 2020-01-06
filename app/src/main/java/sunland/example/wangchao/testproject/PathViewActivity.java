package sunland.example.wangchao.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.mytest.TestActivity;
import com.example.wangchao.testproject.R;

import sunland.example.wangchao.testproject.activity.BaseActivity;

import widget.PathView;

public class PathViewActivity extends TestActivity {
//    private PathView mPathView;
//
    public static void startActivity(Context context){
        Intent i = new Intent(context, PathViewActivity.class);
        context.startActivity(i);
    }
//
//    @Override
//    public int bindLayout() {
//        return R.layout.activity_path_view;
//    }
//
//    @Override
//    public void initViews() {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mPathView = findViewById(R.id.path_view);
//        Log.d("wangchao", "onCreate: " + mPathView.getWidth() + "----" + mPathView.getHeight());
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        ViewTreeObserver observer = mPathView.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.d("wangchao", "onGlobalLayout: " + mPathView.getWidth() + "-----" + mPathView.getHeight());
//            }
//        });
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
