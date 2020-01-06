package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Paint mPaint;
    private Path mPath;
    private Rect mRect;

    public PathView(Context context) {
        super(context);
        initView();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();

        //画折线
//        mPath.lineTo(100,100);
//        mPath.lineTo(300, 100);
//        mPath.lineTo(100, 50);

        //先移动起点，画折线，在封闭
//        mPath.moveTo(50, 50);
//        mPath.lineTo(100,100);
//        mPath.lineTo(300, 100);
//        mPath.close();

        mRect = new Rect(100, 100, 300, 250);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(mRect, mPaint);
//        canvas.drawOval(new RectF(mRect), mPaint);
        mPath.reset();
        mPath.addArc(new RectF(mRect), 0, 90);//从0度开始沿着椭圆画弧线到90度
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果view在布局中使用wrap_content ,这时候就是AT_MOST,我们需要在onmeasure里面做特殊处理，否则和match_parent就没有区别了
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(500, 300);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, 300);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(500, heightSpecSize);
        }
    }
}
