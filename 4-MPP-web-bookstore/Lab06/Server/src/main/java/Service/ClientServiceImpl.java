package Service;

import Domain.Client;
import Repository.DBRepository.ClientRepository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.RemoteException;
import java.util.List;


public class ClientServiceImpl implements ClientService
{
    @Override
    public List<Client> findAll() throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        return clientRepository.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        return clientRepository.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        return clientRepository.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        ClientRepository clientRepository = context.getBean(ClientRepository.class);

        return clientRepository.delete(id);
    }
}
