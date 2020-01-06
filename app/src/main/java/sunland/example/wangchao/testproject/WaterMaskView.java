package sunland.example.wangchao.testproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangchao.testproject.R;

/**
 * 自定义水印view
 * 支持设置logo、公司名称、相关信息
 */
public class WaterMaskView extends RelativeLayout {

    private TextView mTime, mDate, mLocation, mName, mPart;
    private View mainView;
    private ImageView mAvataImg, mLocationImg;
    public WaterMaskView(Context context) {
        this(context, null);
    }

    public WaterMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mainView = LayoutInflater.from(context).inflate(R.layout.layout_watermask, this, true);
        initView();
    }

    private void initView() {
        mTime = mainView.findViewById(R.id.water_time);
        mDate = mainView.findViewById(R.id.water_date);
        mLocation = mainView.findViewById(R.id.water_location);
        mName = mainView.findViewById(R.id.water_name);
        mPart = mainView.findViewById(R.id.water_part);
        mAvataImg = mainView.findViewById(R.id.water_avata);
        mLocationImg = mainView.findViewById(R.id.ic_water_location);
        mPart.setMaxWidth(ScreenUtils.getScreenWidth() - 60);
        mPart.setMaxWidth(ScreenUtils.getScreenWidth() - 45);
        mName.setMaxWidth(ScreenUtils.getScreenWidth() - 80);
        mLocation.setMaxWidth(ScreenUtils.getScreenWidth() - 60);
    }

    public void setTime(String time) {
        mTime.setText(time);
    }

    public void setDate(String date) {
        mDate.setText(date);
    }

    public void setLocation(String location) {
        mLocation.setText(location);
    }

    public void setName(String name) {
        mName.setText(name);
    }

    public void setPart(String part) {
        mPart.setText(part);
    }

    public void reSizeSize(double scaleRation){
        mTime.setTextSize((float) (scaleRation * 20));
        mDate.setTextSize((float) (scaleRation * 16));
        mLocation.setTextSize((float) (scaleRation * 12));
        mName.setTextSize((float) (scaleRation * 12));
        mPart.setTextSize((float) (scaleRation * 12));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = (int) (scaleRation * 51);
        params.height = (int) (scaleRation * 59);
        mLocationImg.setLayoutParams(params);

        params.width = (int) (scaleRation * 36);
        params.height = (int) (scaleRation * 39);
        mAvataImg.setLayoutParams(params);
    }

    public static float dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
