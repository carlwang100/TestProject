package sunland.example.wangchao.testproject;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.wangchao.testproject.R;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.mmkv.MMKV;

public class TestApplication extends Application {

    private RefWatcher refWatcher;
//
    public TestApplication(){
        Log.e("wangchao", "TestApplication: ");
//        getApplicationContext().getResources().getDrawable(R.drawable.ic_launcher_background);
    }

    @Override
    public void onCreate() {
        Log.e("wangchao", "onCreate: ");
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)){
//            return;
//        }
//        refWatcher = LeakCanary.install(this);

        String rootDir = MMKV.initialize(this);
        Log.e("wangchao", "mmkv root dir---->>>" + rootDir);
    }

    @Override
    protected void attachBaseContext(Context base) {
        Log.e("wangchao", "attachBaseContext: ");
        super.attachBaseContext(base);
    }

}
