package sunland.example.wangchao.testproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.wangchao.testproject.R;

import java.util.ArrayList;
import java.util.List;

import sunland.example.wangchao.testproject.WaterMarkBg;

public class WaterImgActivity2 extends Activity {

    public static void  startActivity(Context context) {
        Intent i = new Intent(context, WaterImgActivity2.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_waterimg);
        TextView bg_tv = findViewById(R.id.bg_img);
        List<String> labels = new ArrayList<>();
        labels.add("用户名：张三");
        bg_tv.setBackgroundDrawable(new WaterMarkBg(this,labels,-18,13));
    }
}
