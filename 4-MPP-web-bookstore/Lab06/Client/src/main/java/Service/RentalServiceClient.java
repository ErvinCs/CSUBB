package Service;

import Domain.Rental;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.List;

public class RentalServiceClient implements RentalService
{
    @Autowired
    private RentalService rService;

    @Override
    public List<Rental> findAll() throws RemoteException {
        return rService.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        return rService.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        return rService.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        return rService.delete(id);
    }
}
