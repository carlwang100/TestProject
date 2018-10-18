package okhttp.chainmode;

import android.util.Log;

public class ControHandler2 extends Handler {

    @Override
    void next() {
        if (getNextHandler() != null){
            getNextHandler().next();
        }else {
            Log.d("", "next: 结束");
        }
    }
}
