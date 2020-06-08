package aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aidl.Computer;

public class ComputerManagerService extends Service {

    List<Computer> datas = new ArrayList<>();

    ComputerStub stub = new ComputerStub() {
        @Override
        public List<Computer> getComputers() {
            return datas;
        }

        @Override
        public void addComputer(Computer computer)  {
            datas.add(computer);
            Log.d("service", "addComputer: "+ datas.toArray());
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
