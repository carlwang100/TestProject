package camera2;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Size;


class CompareSizeByArea implements java.util.Comparator<Size> {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int compare(Size lhs, Size rhs) {
        return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                (long) rhs.getWidth() * rhs.getHeight());
    }
}
