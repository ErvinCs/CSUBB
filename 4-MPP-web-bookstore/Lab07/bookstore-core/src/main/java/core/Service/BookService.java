package core.Service;
import core.Domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface BookService {

    List<Book> findAll();
    Book addBook(Book book);
    Optional<Book> findOne(Long id);
    Optional<Book> updateBook(Long id, Book newBook);
    void deleteBook(Long id);
}
