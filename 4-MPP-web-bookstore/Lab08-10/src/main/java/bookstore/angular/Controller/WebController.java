package bookstore.angular.Controller;


import bookstore.angular.Model.Book;
import bookstore.angular.Model.Client;
import bookstore.angular.Model.Rental;
import bookstore.angular.Repository.BookRepository;
import bookstore.angular.Repository.ClientRepository;
import bookstore.angular.Repository.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class WebController {

    private BookRepository bookRepository;
    private ClientRepository clientRepository;
    private RentalRepository rentalRepository;

    public WebController(BookRepository bookRepository, ClientRepository clientRepository, RentalRepository rentalRepository)
    {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.rentalRepository = rentalRepository;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {return bookRepository.findAll();}

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public Iterable<Client> getAllClients(){return clientRepository.findAll();}

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public Iterable<Rental> getAllRentals(){return rentalRepository.findAll();}


    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public Optional<Client> showClient(@PathVariable Long id)
    {
        return clientRepository.findById(id);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public Optional<Book> showBook(@PathVariable Long id)
    {
        return bookRepository.findById(id);
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.GET)
    public Optional<Rental> showRental(@PathVariable Long id)
    {
        return rentalRepository.findById(id);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book)
    {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setISBN(book.getISBN());

        bookRepository.save(newBook);
        return newBook;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity addClient(@RequestBody Client client)
    {
        Client nc = new Client();
        nc.setName(client.getName());
        nc.setCountry(client.getCountry());

        clientRepository.save(nc);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public Rental addRental(@RequestBody Rental rental)
    {
        Rental r = new Rental();
        r.setBookID(rental.getBookID());
        r.setClientID(rental.getClientID());

        rentalRepository.save(r);
        return r;
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable Long id)
    {
        bookRepository.deleteById(id);
        return "";
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable Long id)
    {
        clientRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRental(@PathVariable Long id)
    {
        rentalRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book, @PathVariable Long id)
    {
        Book b = bookRepository.findById(id).orElse(null);
        if(book.getTitle() != null)
            b.setTitle(book.getTitle());
        if(book.getAuthor() != null)
            b.setAuthor(book.getAuthor());
        if(book.getISBN() > 0)
            b.setISBN(book.getISBN());

        bookRepository.save(b);

        return book;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public Client updateClient(@PathVariable Long id, @RequestBody Client client)
    {
        Client c = clientRepository.findById(id).orElse(null);
        if(client.getName() != null)
            c.setName(client.getName());
        if(client.getCountry() != null)
            c.setCountry(client.getCountry());

        clientRepository.save(c);

        return client;
    }
}
