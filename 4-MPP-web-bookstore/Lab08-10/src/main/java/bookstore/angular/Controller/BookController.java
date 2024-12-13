package bookstore.angular.Controller;

import bookstore.angular.Model.Book;
import bookstore.angular.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(String title, String author, int isbn)
    {
        Book b = new Book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setISBN(isbn);
        bookRepository.save(b);
        return b;
    }

    public String removeBook(Long id)
    {
        bookRepository.deleteById(id);
        return "DELETED";
    }

    public String updateBook(Long id, String title, String author, int isbn)
    {
        Optional<Book> book = bookRepository.findById(id);
        if(!title.equals("\n"))
            book.get().setTitle(title);
        if(!author.equals("\n"))
            book.get().setAuthor(author);
        book.get().setISBN(isbn);

        bookRepository.deleteById(id);
        bookRepository.save(book.get());

        return "UPDATED";

    }

    public Iterable<Book> getAll()
    {
        //return JSON or XML with all books
        return bookRepository.findAll();
    }
}
