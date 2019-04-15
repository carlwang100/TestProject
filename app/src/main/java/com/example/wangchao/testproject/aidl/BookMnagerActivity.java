package com.example.wangchao.testproject.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.wangchao.testproject.IBookManager;
import com.example.wangchao.testproject.IOnNewBookArriveListen;
import com.example.wangchao.testproject.R;
import com.example.wangchao.testproject.activity.BaseActivity;
import com.example.wangchao.testproject.data.Book;
import com.example.wangchao.testproject.service.BookManagerService;

import java.util.Arrays;
import java.util.List;


public class BookMnagerActivity extends BaseActivity {
    private final static String TAG = "BookMnagerActivity";
    @Override
    public int bindLayout() {
        return R.layout.activity_aidl;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    IBookManager bookManager;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = IBookManager.Stub.asInterface(service);
            try {

//                Log.d("BookMnagerActivity", "query book list: -------" + Arrays.asList(list));
//                list.add(new Book(3, "web进阶"));
//                Log.d("BookMnagerActivity", "query book list: -------" + Arrays.asList(bookManager.getBookList()));

                bookManager.registerListen(onNewBookArriveListen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    IOnNewBookArriveListen onNewBookArriveListen = new IOnNewBookArriveListen.Stub() {
        @Override
        public void OnNewBookArrived(Book newBook) throws RemoteException {
            List<Book> list = bookManager.getBookList();
            Log.d("BookMnagerActivity", "query book list: -------" + Arrays.asList(list));
            Log.d(TAG, "OnNewBookArrived: " + newBook.toString());
        }
    };

    public static void startActivity(Context context) {
        Intent i = new Intent(context, BookMnagerActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bookManager != null && bookManager.asBinder().isBinderAlive()){
            try {
                bookManager.unregisterListen(onNewBookArriveListen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
    }
}
