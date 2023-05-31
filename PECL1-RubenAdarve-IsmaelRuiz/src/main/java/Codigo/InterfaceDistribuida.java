package Codigo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceDistribuida extends Remote {

    String getBuzon() throws RemoteException;

    String getfurgo1() throws RemoteException;

    String getfurgo2() throws RemoteException;

    String getCartasBuzon() throws RemoteException;
}
