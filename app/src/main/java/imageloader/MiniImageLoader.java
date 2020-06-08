package imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MiniImageLoader extends ImagerLoader {

    private volatile static MiniImageLoader sMiniImageLoader = null;
    private ImageCache mImageCache = null;
    private BitmapConfig config = null;
    private BitmapFactory.Options options = null;
    public static MiniImageLoader getInstance() {
        if (null == sMiniImageLoader){
            synchronized (MiniImageLoader.class){
                if (null == sMiniImageLoader){
                    sMiniImageLoader = new MiniImageLoader();
                }
            }
        }
        return sMiniImageLoader;
    }

   public MiniImageLoader(){
//        mImageCache = new ImageCache();
        ImageView imageView = getTatgetImageView();
        config = new BitmapConfig(imageView.getWidth(), imageView.getHeight());

    }

    @Override
    protected Bitmap downLoadBitmap(String url) {
        HttpURLConnection connection = null;
        InputStream in = null;
        try {
            final URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            in = connection.getInputStream();
            options = config.getBitmapOptions(in);
            Bitmap bitmap = decodeSampleBitmapFromStream(in, options);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
                connection = null;
            }

            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private Bitmap decodeSampleBitmapFromStream(InputStream in, BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(in, null, options);
    }
}
