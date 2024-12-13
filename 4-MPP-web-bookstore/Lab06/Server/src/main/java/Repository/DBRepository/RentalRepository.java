package Repository.DBRepository;

import Domain.Rental;

import java.util.List;

public interface RentalRepository {
    List<Rental> findAll();
    String add(String item);
    String update(String item);
    String delete(Long id);
}
