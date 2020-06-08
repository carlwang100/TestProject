package imageloader;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * 图片加载的基类
 * */
public abstract class ImagerLoader {

    private boolean mExitTasksEarly = false; //是否提前结束
    protected boolean mPauseWork = false;
    private final Object mPauseWorkLock = new Object(); //用于线程同步锁
    private ImageView imageView;
    protected ImagerLoader(){

    }

    public ImageView getTatgetImageView() {
        return imageView;
    }

    public void loadImage(String url, ImageView imageView){
        if (url == null){
            throw new IllegalArgumentException("url can not be null");
        }
        this.imageView = imageView;
        BitmapDrawable bitmapDrawable = null;
        if (bitmapDrawable != null){
            imageView.setImageDrawable(bitmapDrawable);
        }else {
            final BitmapLoadTask task = new BitmapLoadTask(url, imageView);
            // 
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private class BitmapLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
        private final WeakReference<ImageView> imageViewWeakReference;

        public BitmapLoadTask(String url, ImageView imageView) {
            this.url = url;
            imageViewWeakReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap bitmap = null;
            BitmapDrawable drawable = null;
            synchronized (mPauseWorkLock){
                while (mPauseWork && !isCancelled()){
                    try {
                        mPauseWorkLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
//
//            if (bitmap == null && !isCancelled()
//                    && imageViewWeakReference.get() != null && !mExitTasksEarly) {
//                bitmap = getmImageCache().getBitmapFromDisk(mUrl, mBitmapConfig);
//            }
//
//            if (bitmap == null && !isCancelled()
//                    && imageViewWeakReference.get() != null && !mExitTasksEarly) {
//                bitmap = downLoadBitmap(mUrl, mBitmapConfig);
//            }
//            if (bitmap != null) {
//                getmImageCache().addToCache(mUrl, bitmap);
//            }
//
//            if (bitmap == null && !isCancelled() && imageViewWeakReference.get() != null){
//               bitmap = downLoadBitmap(url);
//            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled() || mExitTasksEarly){
                bitmap = null;
            }
            ImageView imageView = imageViewWeakReference.get();
            if (bitmap != null && imageView != null){
                setImageBitmap(imageView, bitmap);
            }
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled();
            synchronized (mPauseWorkLock){
                mPauseWorkLock.notifyAll();
            }
        }
    }

    public void setPauseWork(boolean pauseWork){
        synchronized (mPauseWorkLock){
            mPauseWork = pauseWork;
            if (!mPauseWork){
                mPauseWorkLock.notifyAll();
            }
        }
    }

    private void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    protected abstract Bitmap downLoadBitmap(String url);
}
