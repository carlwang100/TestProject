package aidl.server;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

import aidl.Computer;

//说明自己的能力，并且承诺提供服务
public interface IComputerManager extends IInterface {

    List<Computer> getComputers() throws RemoteException;

    void addComputer(Computer computer) throws RemoteException;
}
