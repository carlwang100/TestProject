package imageloader;

import java.io.File;

import okhttp3.internal.cache.DiskLruCache;

public class ImageCache {
    //todo 需要copy谷歌的Lrucache 类
    private Object mDiskCacheLock = new Object();

    ImageCache(final long cacheSize, final File cacheFile){
        init(cacheSize, cacheFile);
    }

    private void init(final long cacheSize, final File cacheFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mDiskCacheLock){
                    if (!cacheFile.exists()){
                        cacheFile.mkdir();
                    }

//                    DiskLruCache.create(cacheFile, 1, 1, cacheSize);
                }
            }
        }).start();
    }

}
