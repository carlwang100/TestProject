package aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import aidl.Computer;

public class ComputerManagerService extends Service {


    List<Computer> datas = new ArrayList<>();

    //服务端的实现方法
    ComputerStub stub = new ComputerStub() {
        @Override
        public List<Computer> getComputers() {
            Log.d("service", "getComputers: " + Arrays.toString(new List[]{datas}));
            return datas;
        }

        @Override
        public void addComputer(Computer computer)  {
            datas.add(computer);
            Log.d("service", "addComputer: "+ computer.getName() + new Random().nextInt(10));
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

    @Override
    public String toString() {
        return "ComputerManagerService{" +
                "datas=" + datas +
                '}';
    }
}
