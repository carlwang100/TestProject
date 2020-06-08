package imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * 配置图片参数
 */
public class BitmapConfig {

    private int mWidth, mHeight;
    private Bitmap.Config mPreferred;

    public BitmapConfig(int width, int height){
        this.mHeight = height;
        this.mWidth = width;
        this.mPreferred = Bitmap.Config.RGB_565;
    }


    public BitmapConfig(int width, int height, Bitmap.Config preferred){
        this.mHeight = height;
        this.mWidth = width;
        this.mPreferred = preferred;
    }

    public BitmapFactory.Options getBitmapOptions(){
        return getBitmapOptions(null);
    }


    /**
     * 根据imageview的大小动态设置图片的像素大小
     * 这里先解码，再计算宽高比
     * */
    public BitmapFactory.Options getBitmapOptions(InputStream is) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (is != null){
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);
            options.inSampleSize = calculateInSampleSize(options, mHeight, mWidth);
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    //重新计算采样率
    private int calculateInSampleSize(BitmapFactory.Options options, int mHeight, int mWidth) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1; //设置采样率
        if (height > mHeight || width > mWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > mHeight && (halfWidth /inSampleSize) > mWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
