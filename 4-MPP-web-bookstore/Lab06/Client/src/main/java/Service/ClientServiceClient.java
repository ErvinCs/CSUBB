package Service;

import Domain.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.List;

public class ClientServiceClient implements ClientService
{
    @Autowired
    private ClientService cService;

    @Override
    public List<Client> findAll() throws RemoteException {
        return cService.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        return cService.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        return cService.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        return cService.delete(id);
    }
}
