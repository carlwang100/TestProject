package sunland.example.wangchao.testproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.wangchao.testproject.R;
import sunland.example.wangchao.testproject.WaterMaskUtil;
import sunland.example.wangchao.testproject.WaterMaskView;

public class WaterImgActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener  {

    private ImageView ivWaterMark;
    private RadioGroup rgWaterMark;

    private WaterMaskView waterMaskView;
    private Bitmap sourBitmap;
    private Bitmap waterBitmap;
    private Bitmap watermarkBitmap;

    private final static int LEFT_TOP=0;
    private final static int RIGHT_TOP=1;
    private final static int RIGHT_BOTTOM=2;
    private final static int LEFT_BOTTOM=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_img);

        ivWaterMark= (ImageView) findViewById(R.id.wartermark_pic);
        rgWaterMark= (RadioGroup) findViewById(R.id.rg_wartermark);

        rgWaterMark.setOnCheckedChangeListener(this);

        sourBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.smile_recognize_test5);

        waterMaskView = new WaterMaskView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        waterMaskView.setLayoutParams(params);
        waterMaskView.setDate("2012.09.21");
        waterMaskView.setTime("09:02");
        waterMaskView.setLocation("湖北省武汉市洪山区光谷梦工厂");
        waterMaskView.setPart("研发中心/员工平台/android组开发");
        waterMaskView.setName("郑小姐郑小姐");

        int width = sourBitmap.getWidth();
        int height = sourBitmap.getHeight();
        double scaleRation = Math.min((double)width / 829, (double) height / 1469);
        waterMaskView.reSizeSize(scaleRation);


        //默认设置水印位置在左下
        saveWaterMask(LEFT_TOP);
    }

    public static void  startActivity(Context context) {
        Intent i = new Intent(context, WaterImgActivity.class);
        context.startActivity(i);
    }

    /**
     * @param position 左上为0，顺时针算起
     */
    private void saveWaterMask(int position) {
        waterBitmap = WaterMaskUtil.convertViewToBitmap(waterMaskView);

        //根据原图处理要生成的水印的宽高
        float width = sourBitmap.getWidth();
        float height = sourBitmap.getHeight();
        float be = width / height;

//        if ((float) 16 / 9 >= be && be >= (float) 4 / 3) {
//            Log.d("wangchao", "saveWaterMask: --->>>> [4:3, 16:9]" );
//            //在图片比例区间内16;9~4：3内，将生成的水印bitmap设置为原图宽高各自的1/5
//            waterBitmap = WaterMaskUtil.zoomBitmap(waterBitmap, (int) width * 2 / 5, (int) height * 2 / 5);
//        } else if (be > (float) 16 / 9) {
//            Log.d("wangchao", "saveWaterMask: --->>>> (16:9, ----]" );
//            //生成4：3的水印
//            waterBitmap = WaterMaskUtil.zoomBitmap(waterBitmap, (int) width / 5, (int) width * 3 / 20);
//        } else if (be < (float) 4 / 3) {
//            Log.d("wangchao", "saveWaterMask: --->>>> (---, 4:3)" );
//            //生成4：3的水印
//            waterBitmap = WaterMaskUtil.zoomBitmap(waterBitmap, (int) width * 3 / 5, (int) height * 1 / 5);
//        }

        watermarkBitmap = WaterMaskUtil.createWaterMaskLeftTop(this, sourBitmap, waterBitmap, 0, 0);
        ivWaterMark.setImageBitmap(watermarkBitmap);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.rb_wartermark_lefttop:
                saveWaterMask(LEFT_TOP);
                break;
            case R.id.rb_wartermark_righttop:
                saveWaterMask(RIGHT_TOP);
                break;
            case R.id.rb_wartermark_rightbottom:
                saveWaterMask(RIGHT_BOTTOM);
                break;
            case R.id.rb_wartermark_leftbottom:
                saveWaterMask(LEFT_BOTTOM);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sourBitmap != null) {
            sourBitmap.recycle();
            sourBitmap = null;
        }
        if (waterBitmap != null) {
            waterBitmap.recycle();
            waterBitmap = null;
        }
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }
}
