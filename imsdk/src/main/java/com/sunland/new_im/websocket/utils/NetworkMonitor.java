package com.sunland.new_im.websocket.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class NetworkMonitor {

    public static final String NETWORK_CHANGE = "com.sunlands.im.RECEIVED_NETWORK_CHANGE";
    public static final String NET_AVAILABLE = "available";
    public static final String NET_TYPE = "netType";


    public static NetworkMonitor sInstance = new NetworkMonitor();

    private boolean mLastAvailable;
    private NetworkUtils.NetworkType mLastNetworkType;

    private NetworkReceiver mNetworkReceiver;
    private boolean mHasRegist = false;

    public static NetworkMonitor instance() {
        return  sInstance;
    }


    public void register(Context context) {
        if (mNetworkReceiver == null){
            mNetworkReceiver = new NetworkReceiver();
        }

        if(mHasRegist) {
            return;
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        try {
            context.registerReceiver(mNetworkReceiver, filter);
            mHasRegist = true;
        } catch (Exception e) {
            Log.e("NetworkMonitor", e.getMessage());
        }
    }

    public void unRegister(Context context) {
        if(!mHasRegist) {
            return;
        }

        try {
            context.unregisterReceiver(mNetworkReceiver);
        } catch (Exception e) {
        }
        mHasRegist = false;
        mNetworkReceiver = null;
    }

    private void notifyChanged(Context context, boolean available, NetworkUtils.NetworkType networkType) {
        Intent intent = new Intent(NETWORK_CHANGE);
        intent.putExtra(NET_AVAILABLE, available);
        intent.putExtra(NET_TYPE, networkType);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean available = true;
            try {
                if (intent.hasExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY)) {
                    available = !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                } else {
                    available = NetworkUtils.isConnected(context);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            NetworkUtils.NetworkType networkType = NetworkUtils.getNetworkType(context);
            if (mLastAvailable == available && mLastNetworkType == networkType) {
                return;
            }
            mLastAvailable = available;
            mLastNetworkType = networkType;

            notifyChanged(context, mLastAvailable, mLastNetworkType);
            Log.i("NetworkReceiver","网络状态改变: " + mLastNetworkType + "avaliable: " + mLastAvailable);
        }
    }
}
