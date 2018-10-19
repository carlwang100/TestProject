package widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.wangchao.testproject.R;
import com.example.wangchao.testproject.activity.BaseActivity;

public class RoundImageActivity extends BaseActivity {

    private ImageView mShaderImg;

    public static void startActivity(Context context) {
        Intent i = new Intent(context, RoundImageActivity.class);
        context.startActivity(i);
    }

    @Override
    public int bindLayout() {
        return R.layout.round_image_layout;
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
        mShaderImg = findViewById(R.id.bitmap_shader_image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.item_topics_listview_bcg_more);
        mShaderImg.setImageBitmap(roundBitmapByShader(bitmap,
                (int) getResources().getDimension(R.dimen.round_image_width),
                (int) getResources().getDimension(R.dimen.round_image_height),
                (int) getResources().getDimension(R.dimen.radius)
                )
        );
    }

    private Bitmap roundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius) {
        if (bitmap == null)
            throw new NullPointerException("Bitmap can not be null");
        //初始化缩放比
        float widthScale = outWidth * 1.0f / bitmap.getWidth();
        float heightScale = outHeight * 1.0f / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);

        //初始化绘制纹理图
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);

        //初始化目标bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(bitmapShader);

        canvas.drawRoundRect(new RectF(0, 0, outWidth, outHeight), radius, radius, paint);
        return targetBitmap;
    }

}
