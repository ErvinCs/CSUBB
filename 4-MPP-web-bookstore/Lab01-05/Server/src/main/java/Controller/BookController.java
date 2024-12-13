package Controller;

import Domain.Book;
import Domain.Validators.ValidatorException;
import Repository.DBRepository.BookDBRepository;
import Repository.Repository;
import Service.HandlerService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController
{ private BookDBRepository<Long, Book> repository;

    public BookController(BookDBRepository<Long, Book> repository) {
        this.repository = repository;
    }

    public void add(Book item) throws ValidatorException
    {
        //Book
        repository.saveRec(item);
    }

    public Set<Book> getAll()
    {
        //getAll
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all books whose name contain the given string.
     * @param s
     * @return
     */
    public Set<Book> filter(String s)
    {
        //BooksByName
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks = new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getName().contains(s));

        if(filteredBooks.isEmpty())
            System.out.println("No such books!");

        return filteredBooks;
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public void update(Book item) throws ValidatorException
    {
        repository.updateRec(item);
    }

}

