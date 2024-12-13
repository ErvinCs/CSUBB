package bookstore.angular.Controller;


import bookstore.angular.Model.Client;
import bookstore.angular.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    public String addClient(String name, String country)
    {
        Client c = new Client();
        c.setName(name);
        c.setCountry(country);
        clientRepository.save(c);

        return "SAVED";
    }

    public String removeClient(Long id)
    {
        clientRepository.deleteById(id);

        return "DELETED";
    }

    public String updateClient(Long id, String name, String country)
    {
        Optional<Client> client = clientRepository.findById(id);
        if(!name.equals("\n"))
            client.get().setName(name);
        if(!country.equals("\n"))
            client.get().setCountry(country);

        clientRepository.deleteById(id);
        clientRepository.save(client.get());

        return "UPDATED";
    }

    public Iterable<Client> getAll()
    {
        //return JSON or XML with all books
        return clientRepository.findAll();
    }
}
