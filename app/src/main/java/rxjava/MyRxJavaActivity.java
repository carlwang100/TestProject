package rxjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangchao.testproject.R;

import java.util.ArrayList;

public class MyRxJavaActivity extends Activity {

    public static void startActivity(Context context) {
        Intent i = new Intent(context, MyRxJavaActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        init();
    }

    private ArrayList<String> list = new ArrayList<>();

    private void init() {
        list.add("1");
        list.add("2");
        Observable.just(list)
                .map(new Function<ArrayList<String>, ArrayList>() {
                    @Override
                    public ArrayList apply(ArrayList s){
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < s.size(); i++) {
                            list.add(s.get(i) + "变换后的");
                        }
                        return list;
                    }

                })
                .subscribe(new Observer<ArrayList>() {
                    @Override
                    public void onSubscribe() {
                        Log.e("tag", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ArrayList s) {
                        for (int i = 0; i < s.size(); i++) {
                            Log.e("tag", "onNext: " + s.get(i));
                        }
//

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag", "onComplete: ");
                    }
                });
    }
}
