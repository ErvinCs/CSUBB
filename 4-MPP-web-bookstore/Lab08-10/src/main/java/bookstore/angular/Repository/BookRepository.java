package bookstore.angular.Repository;

import bookstore.angular.Model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long id);

    @Override
    void delete(Book deleted);
}