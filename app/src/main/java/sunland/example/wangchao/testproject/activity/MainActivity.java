package sunland.example.wangchao.testproject.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import camera2.CameraActivity;
import sunland.example.wangchao.testproject.PathViewActivity;
import com.example.wangchao.testproject.R;

import java.text.DecimalFormat;

import okhttp.OkhttpActivity;
import rxjava.RxJavaActivity;
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
        ClassLoader loader = this.getClassLoader().getParent();
        Log.d("wangchao", "onCreate: ---" + loader.toString());
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
        findViewById(R.id.aidl_btn).setOnClickListener(this);
        findViewById(R.id.rxjava_btn).setOnClickListener(this);
        findViewById(R.id.camera_btn).setOnClickListener(this);
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
                WaterImgActivity2.startActivity(this);
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
            case R.id.aidl_btn:
                break;
            case R.id.rxjava_btn:
                RxJavaActivity.startActivity(this);
                break;
            case R.id.camera_btn:
                CameraActivity.startActivity(this);
                break;
        }
    }
}
