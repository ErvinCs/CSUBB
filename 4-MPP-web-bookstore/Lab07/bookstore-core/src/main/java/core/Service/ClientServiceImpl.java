package core.Service;

import core.Domain.Client;
import core.Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        log.trace("findAll");

        List<Client> clients = clientRepository.findAll();

        log.trace("findAll: clients={}", clients);

        return clients;
    }

    @Override
    @Transactional
    public Client addClient(Client client) {
        log.trace("addClient: client={}", client);

        clientRepository.save(client);

        log.trace("addClient --- method finished");

        return client;
    }

    @Override
    public Optional<Client> findOne(Long id) {
        log.trace("findClient: clientId={}", id);

        Optional<Client> client = clientRepository.findById(id);

        log.trace("findClient: client={}", client);

        return client;
    }

    @Override
    @Transactional
    public Optional<Client> updateClient(Long id, Client newClient) {
        log.trace("updateClient: id={}, newClient={}", id, newClient);

        Optional<Client> clientOptional = clientRepository.findById(id);

        clientOptional.ifPresent(c -> {
                    c.setCountry(newClient.getCountry());
                    c.setName(newClient.getName());
                }
        );

        log.trace("updateClient: clientOptional={}", clientOptional);

        return clientOptional;
    }


    @Override
    @Transactional
    public void deleteClient(Long id) {
        log.trace("deleteClient: id={}", id);

        clientRepository.deleteById(id);

        log.trace("deleteClient --- method finished");

    }

}
