package core.Service;

import core.Domain.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

    List<Client> findAll();
    Client addClient(Client client);
    Optional<Client> findOne(Long id);
    Optional<Client> updateClient(Long id, Client newClient);
    void deleteClient(Long id);
}
