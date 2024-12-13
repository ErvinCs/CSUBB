package Service;

import Domain.Client;
import Domain.Validators.ClientValidator;
import Domain.Validators.ValidatorException;
import Repository.InMemoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ClientServiceTest {

    private ClientValidator validator=new ClientValidator();
    private InMemoryRepository<Long, Client> repo=new InMemoryRepository<>(validator);
    private ClientService clientservice = new ClientService(repo);
    private Client c1=new Client("Client1","Country1");
    private Client c2=new Client("Client2","Country2");

    @Test
    public void testAddBook() throws ValidatorException
    {
        c1.setID((long)1);
        c2.setID((long)2);

        clientservice.add(c1);
        clientservice.add(c2);

        Set<Client> clients=new HashSet<>();
        clients.add(c1);
        clients.add(c2);
        assertEquals("Repo lists should be equal.",clients, repo.findAll());

    }

    private void assertEquals(String s, Set<Client> clients, Iterable<Client> all) {
    }

    @Test
    public void testGetAllBooks()
    {
        c1.setID((long)1);
        c2.setID((long)2);

        clientservice.add(c1);
        clientservice.add(c2);

        assertEquals("Repo lists should be equal.", clientservice.getAll(), repo.findAll());

    }

    @Test
    public void testFilterBooksByName()
    {
        String name = "someClient";
        c1.setID((long)1);
        c2.setID((long)2);

        repo.save(c1);
        repo.save(c2);

        Set<Client> clients=new HashSet<>();

        assertEquals("Set should be emtpy.", clients, clientservice.filter(name));

    }

    @Test
    public void testDelete()
    {
        Client c1 = new Client("c1","ctry1");
        c1.setID(1L);
        Client c2 = new Client("c2","ctry2");
        c2.setID(2L);

        repo.save(c1);
        repo.save(c2);

        Set<Client> clients=new HashSet<>();
        clients.add(c1);
        clientservice.delete(2L);
        assertEquals("Sets should be equal",clients,clientservice.getAll());
    }
    @Test
    public void testUpdate()
    {
        Client c1 = new Client("c1","ctry1");
        c1.setID(1L);
        repo.save(c1);

        Set<Client> clients=new HashSet<>();
        Client c2 = new Client("updatedName","updatedCountry");
        c2.setID(1L);
        clients.add(c2);
        clientservice.update(c2);
        assertEquals("Sets should be equal",clients,clientservice.getAll());
    }
}
