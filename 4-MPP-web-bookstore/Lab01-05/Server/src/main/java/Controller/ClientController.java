package Controller;

import Domain.Client;
import Domain.Validators.ValidatorException;
import Repository.DBRepository.ClientDBRepository;
import Repository.Repository;
import Service.HandlerService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController
{
    private ClientDBRepository<Long, Client> repository;

    public ClientController(ClientDBRepository<Long, Client> repository) {
        this.repository = repository;
    }

    public void add(Client item) throws ValidatorException
    {
        //Client
        repository.saveRec(item);
    }

    public Set<Client> getAll()
    {
        //Clients
        Iterable<Client> clients = repository.findAll();

        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all clients whose name contain the given string.
     * @param s
     * @return
     */
    public Set<Client> filter(String s)
    {
        //ClientsByName
        Iterable<Client> clients = repository.findAll();

        Set<Client> filteredClients = new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(book -> !book.getName().contains(s));

        if(filteredClients.isEmpty())
            System.out.println("No such clients!");

        return filteredClients;
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public void update(Client item) throws ValidatorException
    {
        repository.updateRec(item);
    }
}
