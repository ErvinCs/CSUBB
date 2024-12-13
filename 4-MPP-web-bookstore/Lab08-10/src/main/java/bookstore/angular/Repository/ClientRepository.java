package bookstore.angular.Repository;

import bookstore.angular.Model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Override
    Optional<Client> findById(Long id);

    @Override
    void delete(Client deleted);
}
