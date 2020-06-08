package aidl.client;

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

import com.example.wangchao.testproject.R;

import java.util.Arrays;
import java.util.List;

import aidl.Computer;
import aidl.server.ComputerManagerService;
import aidl.server.ComputerStub;
import aidl.server.IComputerManager;
import sunland.example.wangchao.testproject.activity.BaseActivity;
import thread.ThreadTestActivity;

public class ComputerListActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent i = new Intent(context, ComputerListActivity.class);
        context.startActivity(i);
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_aidl;
    }

    @Override
    public void initViews() {
        findViewById(R.id.add_com).setOnClickListener(this);
        findViewById(R.id.get_com).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.get_com){
            if (remote != null){
                try {
                    List<Computer> list = remote.getComputers();
                    Log.d("aidl", "list-->>>: " + list.toArray());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }else if (id == R.id.add_com){
            if (remote != null){
                Computer computer = new Computer();
                computer.setName("mbp");
                computer.setPrice("170000");
                try {
                    remote.addComputer(computer);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ComputerManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private IComputerManager remote;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("aidl", "onServiceConnected: ");
            remote = ComputerStub.asInterface(service);
            Log.d("aidl", "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("aidl", "onServiceConnected: ");
        }
    };

}
