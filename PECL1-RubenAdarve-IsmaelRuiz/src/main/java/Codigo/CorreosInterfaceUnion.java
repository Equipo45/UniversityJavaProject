package Codigo;
import InterfazGrafica.CorreosSL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CorreosInterfaceUnion  extends UnicastRemoteObject implements InterfaceDistribuida{
    private final CorreosSL interfaces;
    
    public CorreosInterfaceUnion(CorreosSL inter) throws RemoteException{
        this.interfaces = inter;
    }

    @Override
    public String getBuzon() throws RemoteException {
        return interfaces.getBuzon();
    }

    @Override
    public String getfurgo1() throws RemoteException {
        return interfaces.getfurgo1();
    }

    @Override
    public String getfurgo2() throws RemoteException {
        return interfaces.getfurgo2();
    }

    @Override
    public String getCartasBuzon() throws RemoteException {
        return interfaces.getCartasBuzon();
    }
}