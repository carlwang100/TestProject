package aidl.proxy;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import java.util.List;

import aidl.Computer;
import aidl.server.ComputerStub;
import aidl.server.IComputerManager;

public class ComputerProxy implements IComputerManager {

    private IBinder mRemote;

    public ComputerProxy(IBinder binder){
        mRemote = binder;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }


    @Override
    public List<Computer> getComputers() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        List<Computer> ret;
        try {
            _data.writeInterfaceToken(ComputerStub.DESCRIPTOR);
            mRemote.transact(ComputerStub.TRANSACTION_getComputers, _data, _reply, 0);
            _reply.readException();
            ret = _reply.createTypedArrayList(Computer.CREATOR);
        }finally {
            _data.recycle();
            _reply.recycle();
        }
        return ret;
    }

    @Override
    public void addComputer(Computer computer) throws RemoteException {
        //对象池
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(ComputerStub.DESCRIPTOR);
            if (computer != null){
                _data.writeInt(1);
                computer.writeToParcel(_data, 0);
            }else {
                _data.writeInt(0);
            }

            mRemote.transact(ComputerStub.TRANSACTION_addComputer, _data, _reply, 0);
            _reply.readException();
        }finally {
            //释放对象回对象池
            _reply.recycle();
            _data.recycle();
        }

    }

}
