package com.sunland.new_im.db;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.j256.ormlite.misc.BaseDaoEnabled;

/**
 * Created by fengd on 2018/8/27.
 */

public class DBChangeNotifier {

    public static void notifyChanged(Context context, final Class<? extends BaseDaoEnabled> clazz) {
        final Context appContext = context.getApplicationContext();

        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Uri uri = ContentUriFactory.get().getUri(clazz);
                appContext.getApplicationContext().getContentResolver().notifyChange(uri, null);
            }
        });

    }

    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void runOnUIThread(Runnable r) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            r.run();
        } else {
            mUIHandler.post(r);
        }
    }
}
