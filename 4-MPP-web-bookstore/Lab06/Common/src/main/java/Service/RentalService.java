package Service;

import Domain.Rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RentalService extends Remote {
    List<Rental> findAll() throws RemoteException;
    //T findById(Long id) throws RemoteException;
    String add(String item) throws RemoteException;
    String update(String item) throws RemoteException;
    String delete(Long id) throws RemoteException;
}
