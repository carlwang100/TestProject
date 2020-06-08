package touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class GroupA extends LinearLayout {
    public GroupA(Context context) {
        super(context);
    }

    public GroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("wangchao", "dispatchTouchEvent: A");
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d("wangchao", "dispatchTouchEvent: A -->>ACTION_DOWN");
//                return false;
//            case MotionEvent.ACTION_MOVE:
//                Log.d("wangchao", "dispatchTouchEvent: A -->>ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d("wangchao", "dispatchTouchEvent: A -->>ACTION_UP");
//                break;
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("wangchao", "onInterceptTouchEvent: A");
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("wangchao", "onInterceptTouchEvent: A -->>ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("wangchao", "onInterceptTouchEvent: A -->>ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("wangchao", "onInterceptTouchEvent: A -->>ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("wangchao", "onTouchEvent: A");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("wangchao", "onTouchEvent: A -->>ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("wangchao", "onTouchEvent: A -->>ACTION_MOVE");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d("wangchao", "onTouchEvent: A -->>ACTION_UP");
                return false;
        }
        return super.onTouchEvent(event);
    }
}
