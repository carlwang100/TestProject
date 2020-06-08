package imageloader;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;


/**
 *
 * */
public class MemoryCache {
    private final int DEFAULT_MEM_CACHE_SIZE = 1024 * 12;
    private LruCache<String, Bitmap> memCache;

    public MemoryCache(float sizePer){
        init(sizePer);
    }

    private void init(float sizePer) {
        int cacheSize = DEFAULT_MEM_CACHE_SIZE;
        if (sizePer > 0){
            cacheSize = Math.round(sizePer * Runtime.getRuntime().maxMemory() / 1024);
        }
        memCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算出一张图片占用内存大小
                final int bitmapSize = getBitmapSize(value) / 1024;
                return bitmapSize == 0 ? 1 : bitmapSize;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };

    }

    private int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }

        return bitmap.getRowBytes() * bitmap.getHeight();

    }

    public Bitmap get(String url){
        Bitmap bitmap = null;
        if (memCache != null){
            bitmap = memCache.get(url);
        }
        return bitmap;
    }

    public void put(String url, Bitmap bitmap){
        if (url == null || bitmap == null){
            return;
        }
        memCache.put(url, bitmap);
    }

    public void clearCache(){
        if (memCache != null){
            memCache.evictAll();
        }
    }


}
