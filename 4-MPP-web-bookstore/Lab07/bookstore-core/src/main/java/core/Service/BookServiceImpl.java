package core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.Domain.Book;
import core.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        log.trace("findAll");

        List<Book> books = bookRepository.findAll();

        log.trace("findAll: books={}", books);

        return books;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        log.trace("addBook: book={}", book);

        bookRepository.save(book);

        log.trace("addBook --- method finished");

        return book;
    }

    @Override
    public Optional<Book> findOne(Long id) {
        log.trace("findBook: bookId={}", id);

        Optional<Book> book = bookRepository.findById(id);

        log.trace("findBook: book={}", book);

        return book;
    }

    @Override
    @Transactional
    public Optional<Book> updateBook(Long id, Book newBook) {
        log.trace("updateBook: id={}, newBook={}", id, newBook);

        Optional<Book> bookOptional = bookRepository.findById(id);

        bookOptional.ifPresent(b -> {
            b.setAuthor(newBook.getAuthor());
            b.setISBN(newBook.getISBN());
            b.setName(newBook.getName());
            }
        );

        log.trace("updateBook: bookOptional={}", bookOptional);

        return bookOptional;
    }


    @Override
    @Transactional
    public void deleteBook(Long id) {
        log.trace("deleteBook: id={}", id);

        bookRepository.deleteById(id);

        log.trace("deleteBook --- method finished");
    }
}
