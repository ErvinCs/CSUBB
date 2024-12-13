package core.Service;

import core.Domain.Rental;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RentalService {

    List<Rental> findAll();
    Rental addRental(Rental book);
    Optional<Rental> findOne(Long id);
    Optional<Rental> updateRental(Long id, Rental newRental);
    void deleteRental(Long id);
}
