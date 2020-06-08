package aidl.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import aidl.Computer;
import aidl.proxy.ComputerProxy;

public abstract class ComputerStub extends Binder implements IComputerManager {

    public static final java.lang.String DESCRIPTOR = "com.example.wangchao.testproject.IBookManager";


    public static final int TRANSACTION_getComputers = (IBinder.FIRST_CALL_TRANSACTION + 0);
    public static final int TRANSACTION_addComputer = (IBinder.FIRST_CALL_TRANSACTION + 1);

    void ComputerStub(){
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IComputerManager asInterface(IBinder binder){
        if (binder == null) return null;

        IInterface iInterface = binder.queryLocalInterface(DESCRIPTOR);
        //如果不是跨进程，就直接返回
        if (iInterface != null && iInterface instanceof ComputerStub)
            return (IComputerManager) iInterface;

        return new ComputerProxy(binder); //生成代理
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                break;
            case TRANSACTION_addComputer:
                data.enforceInterface(DESCRIPTOR);
                Computer arg = null;
                if (data.readInt() != 0){
                    arg = Computer.CREATOR.createFromParcel(data);
                }
                this.addComputer(arg);
                reply.writeNoException();
                break;
            case TRANSACTION_getComputers:
                data.enforceInterface(DESCRIPTOR);
                List<Computer> ret = this.getComputers();
                reply.writeNoException();
                reply.writeTypedList(ret);
                break;
        }

        return super.onTransact(code, data, reply, flags);
    }

}
