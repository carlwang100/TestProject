package widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangchao.testproject.R;
import com.example.wangchao.testproject.ScreenUtils;

public class ListCustomView extends LinearLayout {
    private Context mContext;
    public ListCustomView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ListCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public ListCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        for (int i = 0; i < 20; i++){
            addView(getItemView(i));
        }
    }

    public View getItemView(int i) {
        return resizeImageView(i);
    }

    private LayoutParams params;

    private View resizeImageView(int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
        View imageView = view.findViewById(R.id.item_image);
        int newWidth = (int) (ScreenUtils.getScreenWidth() / 4.5);
        int newHeigent = newWidth;
        params = new LayoutParams(newWidth, newHeigent);
        imageView.setLayoutParams(params);
        ((ImageView)imageView).setImageResource(R.mipmap.percentage);
        ((TextView)view.findViewById(R.id.item_name)).setText("第" + i + "个");

        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.leftMargin = 20;
        view.setLayoutParams(params1);
        return view;

    }

}
