package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import utils.SizeUtils;

public class XfermodeView extends View {
    private Paint paint, mRecPaint;
    private int mWidth, mHeight;
    private int mRadius = SizeUtils.dp2px(40);
    private float mCenterX, mCenterY;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private RectF mSrcRect, mDestRect;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);

        mRecPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRecPaint.setColor(Color.BLUE);
        mRecPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDestRect = new RectF(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius);
        mSrcRect = new RectF(mCenterX , mCenterY, mCenterX + mRadius, mCenterY + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawRect(mCenterX, mCenterY, mCenterX + mRadius, mCenterY + mRadius, mRecPaint);
        mRecPaint.setColor(Color.YELLOW);
        mRecPaint.setXfermode(xfermode);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mRecPaint);
        mRecPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
