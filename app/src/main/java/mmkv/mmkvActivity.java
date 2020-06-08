package mmkv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.wangchao.testproject.R;
import com.tencent.mmkv.MMKV;

import okhttp.OkhttpActivity;
import sunland.example.wangchao.testproject.activity.BaseActivity;

public class mmkvActivity extends BaseActivity {
    public static void startActivity(Context context){
        Intent i = new Intent(context, mmkvActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_aidl;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode("bool", true);
        Log.e("mmkv", "onCreate: bool->>" + mmkv.decodeBool("bool"));

        mmkv.encode("wangchao", 100);
        Log.e("mmkv", "onCreate: wangchao->>" + mmkv.decodeInt("wangchao"));

        MMKV.mmkvWithID("ss", MMKV.MULTI_PROCESS_MODE);
    }
}
