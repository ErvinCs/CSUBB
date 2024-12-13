package Service;

import Domain.Rental;
import Domain.Validators.RentalValidator;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RentalServiceTest {

    private Validator<Rental> rentalValidator = new RentalValidator();
    private InMemoryRepository<Long, Rental> repo = new InMemoryRepository<Long, Rental>(rentalValidator);
    private RentalService rentalService = new RentalService(repo);

    private Rental r1 = new Rental(1L, 1L);
    private Rental r2 = new Rental(2L, 3L);

    @Test
    public void testAdd()
    {
        r1.setID((long)1);
        r2.setID((long)2);

        rentalService.add(r1);
        rentalService.add(r2);

        Set<Rental> rentals=new HashSet<>();
        rentals.add(r1);
        rentals.add(r2);
        assertEquals("Repo lists should be equal.",rentals, repo.findAll());
    }

    @Test
    public void testGetAll()
    {
        r1.setID((long)1);
        r2.setID((long)2);

        rentalService.add(r1);
        rentalService.add(r2);

        assertEquals("Repo lists should be equal.", rentalService.getAll(), repo.findAll());
    }

    @Test
    public void testFilter()
    {
        String id = "3";
        r1.setID((long)1);
        r2.setID((long)2);

        repo.save(r1);
        repo.save(r2);

        Set<Rental> rentals=new HashSet<>();

        assertEquals("Set should be emtpy.", rentals, rentalService.filter(id));
    }

    @Test
    public void testDelete()
    {
        Rental r1  = new Rental(3L,2L);
        r1.setID(1L);
        Rental r2  = new Rental(30L,20L);
        r2.setID(2L);

        repo.save(r1);
        repo.save(r2);

        Set<Rental> rentals=new HashSet<>();
        rentals.add(r1);
        rentalService.delete(2L);
        assertEquals("Sets should be equal",rentals,rentalService.getAll());
    }

    @Test
    public void testUpdate()
    {
        Rental r1  = new Rental(3L,2L);
        r1.setID(1L);
        repo.save(r1);

        Set<Rental> rentals=new HashSet<>();
        Rental r2  = new Rental(30L,20L);
        r2.setID(1L);
        rentals.add(r2);
        rentalService.update(r2);
        assertEquals("Sets should be equal",rentals,rentalService.getAll());
    }
}
