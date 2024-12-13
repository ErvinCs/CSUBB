package core.Service;

import core.Domain.Rental;
import core.Repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentalServiceImpl implements RentalService {
    private static final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<Rental> findAll() {
        log.trace("findAll");

        List<Rental> rentals = rentalRepository.findAll();

        log.trace("findAll: rentals={}", rentals);

        return rentals;
    }

    @Override
    @Transactional
    public Rental addRental(Rental rental) {
        log.trace("addRental: rental={}", rental);

        rentalRepository.save(rental);

        log.trace("addRental --- method finished");

        return rental;

    }

    @Override
    public Optional<Rental> findOne(Long id) {
        log.trace("findRental: rentalId={}", id);

        Optional<Rental> rental = rentalRepository.findById(id);

        log.trace("findRental: rental={}", rental);

        return rental;
    }

    @Override
    @Transactional
    public Optional<Rental> updateRental(Long id, Rental newRental) {
        log.trace("updateRental: id={}, newRental={}", id, newRental);

        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        rentalOptional.ifPresent(r ->
        {
            r.setBookID(newRental.getBookID());
            r.setClientID(newRental.getClientID());
        });

        log.trace("updateRental: rentalOptional={}", rentalOptional);

        return rentalOptional;
    }


    @Override
    @Transactional
    public void deleteRental(Long id) {
        log.trace("deleteRental: id={}", id);

        rentalRepository.deleteById(id);

        log.trace("deleteRental --- method finished");
    }
}
