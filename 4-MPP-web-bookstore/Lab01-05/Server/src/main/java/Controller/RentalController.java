package Controller;

import Domain.Rental;
import Domain.Validators.ValidatorException;
import Repository.DBRepository.RentalDBRepository;
import Repository.Repository;
import Service.HandlerService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RentalController
{
    private RentalDBRepository<Long, Rental> repository;

    public RentalController(RentalDBRepository<Long, Rental> repository) {
        this.repository = repository;
    }

    public void add(Rental item) throws ValidatorException
    {
        //Rental
        repository.saveRec(item);
    }

    public Set<Rental> getAll()
    {
        //Rentals
        Iterable<Rental> rentals = repository.findAll();
        return StreamSupport.stream(rentals.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all rentals including a given bookID.
     * @param s
     * @return
     */
    public Set<Rental> filter(String s)
    {
        //RentalsByBookID
        Long id = Long.valueOf(s);
        Iterable<Rental> rentals = repository.findAll();

        Set<Rental> filteredRentals = new HashSet<>();
        rentals.forEach(filteredRentals::add);
        filteredRentals.removeIf(rental -> !rental.getBookID().equals(id));

        if(filteredRentals.isEmpty())
            System.out.println("No such rentals!");

        return filteredRentals;
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public void update(Rental item) throws ValidatorException
    {
        repository.updateRec(item);
    }
}

