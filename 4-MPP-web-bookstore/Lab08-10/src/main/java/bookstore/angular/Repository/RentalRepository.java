package bookstore.angular.Repository;

import bookstore.angular.Model.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentalRepository extends CrudRepository<Rental, Long>{

    @Override
    Optional<Rental> findById(Long id);

    @Override
    void delete(Rental deleted);
}
