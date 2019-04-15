package com.example.wangchao.testproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.wangchao.testproject.IBookManager;
import com.example.wangchao.testproject.IOnNewBookArriveListen;
import com.example.wangchao.testproject.data.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BookManagerService extends Service {

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
//    private CopyOnWriteArrayList<IOnNewBookArriveListen> mListenList = new CopyOnWriteArrayList<IOnNewBookArriveListen>();
    private RemoteCallbackList<IOnNewBookArriveListen> mListenList = new RemoteCallbackList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.d("wangchao0000", "getBookList: ");
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
           mBookList.add(book);
        }

        @Override
        public void registerListen(com.example.wangchao.testproject.IOnNewBookArriveListen listen) throws RemoteException {
//            if (!mListenList.contains(listen)){
//                mListenList.add(listen);
//            }
            mListenList.register(listen);
        }

        @Override
        public void unregisterListen(com.example.wangchao.testproject.IOnNewBookArriveListen listen) throws RemoteException {
//            if (mListenList.contains(listen)){
//                mListenList.remove(listen);
//                Log.d("wangchao0000", "unregisterListen: ok ----------->>>>");
//            }else {
//                Log.d("wangchao0000", "faild------->>");
//            }
            mListenList.unregister(listen);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wangchao0000", "onCreate: ");

//        mBookList.add(new Book(1, "开发艺术探索"));
//        mBookList.add(new Book(2, "Flutter进阶"));

        ExecutorService executorService = Executors.newScheduledThreadPool(1);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d("wangchao0000", "run: ");
                int bookId = mBookList.size() + 1;
                notifyNewBookArrived(new Book(bookId, "new book--" + bookId));

            }
        },0, 5, TimeUnit.SECONDS);
    }

    private void notifyNewBookArrived(Book newBook) {
        mBookList.add(newBook);
//        for (IOnNewBookArriveListen listen : mListenList){
//            try {
//                listen.OnNewBookArrived(newBook);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }

        int count = mListenList.beginBroadcast();
        for (int i = 0; i < count; i++){
            IOnNewBookArriveListen listen = mListenList.getBroadcastItem(i);
            if (listen != null){
                try {
                    listen.OnNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            mListenList.finishBroadcast();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
