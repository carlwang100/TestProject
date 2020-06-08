package touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("wangchao", "dispatchTouchEvent: MyView");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("wangchao", "dispatchTouchEvent: MyView -->>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("wangchao", "dispatchTouchEvent: MyView -->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("wangchao", "dispatchTouchEvent: MyView -->>ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

//    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("wangchao", "onTouchEvent: MyView");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("wangchao", "onTouchEvent: MyView -->>ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("wangchao", "onTouchEvent: MyView -->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("wangchao", "onTouchEvent: MyView -->>ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
