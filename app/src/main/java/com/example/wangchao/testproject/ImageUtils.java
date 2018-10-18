package com.example.wangchao.testproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;

public class ImageUtils {


    private static RectF rectF;

    /**
     * 设置水印图片在左上角
     *
     * @param Context
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskLeftTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                dp2px(context, paddingLeft), dp2px(context, paddingTop));
    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 给图片添加文字到左上角
     *
     * @param context
     * @param bitmap
     * @param text
     * @return
     */
    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, int color, int paddingLeft, int paddingTop, boolean hasBg) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + bounds.height(), hasBg);
    }


    /**
     * 获取文字的bounds
     * */
    public static Rect getTextBounds(Context context, String text, int size){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }




    //图片上绘制文字
    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text,
                                           Paint paint, Rect bounds, int paddingLeft, int paddingTop, boolean hasBg) {
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        if (hasBg) {
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setColor(Color.parseColor("#5F999999"));
            p.setStyle(Paint.Style.FILL);
            RectF r = new RectF(paddingLeft - 5, paddingTop - bounds.height() + 5, paddingLeft + bounds.width() + 10, paddingTop + 20);
            canvas.drawRoundRect(r, 4, 4, p);
        }
        canvas.drawText(text, paddingLeft , paddingTop + 10 , paint);
        return bitmap;
    }


    public static Bitmap drawTextAndImgToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, int color, int paddingLeft, int paddingTop, Drawable drawable) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextAndImgToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + drawable.getIntrinsicHeight(), drawable);
    }



    //图片上绘制文字
    private static Bitmap drawTextAndImgToBitmap(Context context, Bitmap bitmap, String text,
                                                 Paint paint, Rect bounds, int paddingLeft, int paddingTop, Drawable drawable) {
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.parseColor("#5F999999"));
        p.setStyle(Paint.Style.FILL);

        //绘制灰色背景
        rectF = new RectF(paddingLeft, paddingTop - drawable.getIntrinsicHeight(), paddingLeft + bounds.width() + 25 + drawable.getIntrinsicWidth(), paddingTop + 20);
        canvas.drawRoundRect(rectF, 4, 4, p);

        //绘制背景里面的图片
        drawable.setBounds(paddingLeft + 10, paddingTop - drawable.getIntrinsicHeight() + 10, paddingLeft + drawable.getIntrinsicWidth() + 10, paddingTop + 10 );
        drawable.draw(canvas);

        //绘制背景里面的文字
        canvas.drawText(text, paddingLeft + drawable.getIntrinsicWidth() + 20, paddingTop + (rectF.height() - getTextBounds(context, text, 12).height()) / 2 - 5, paint);
        return bitmap;
    }

    public static RectF getAvtaAndNameRec(){
        return rectF;
    }

    /**
     * 缩放图片
     *
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }

    /**
     * dip转pix
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
