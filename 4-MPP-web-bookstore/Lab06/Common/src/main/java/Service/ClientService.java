package Service;

import Domain.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientService extends Remote {
    List<Client> findAll() throws RemoteException;
    //T findById(Long id) throws RemoteException;
    String add(String item) throws RemoteException;
    String update(String item) throws RemoteException;
    String delete(Long id) throws RemoteException;
}
