package touchevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.wangchao.testproject.R;

import sunland.example.wangchao.testproject.activity.WaterImgActivity;

public class TouchEventActivity extends AppCompatActivity {


    public static void  startActivity(Context context) {
        Intent i = new Intent(context, TouchEventActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        View view = findViewById(R.id.myview);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("wangchao", "onTouch: ");
                return true;
            }
        });
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("wangchao", "onClick: ");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("wangchao", "onTouchEvent: activity");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("wangchao", "activity:  -->>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("wangchao", "activity:  -->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("wangchao", "activity:  -->>ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
