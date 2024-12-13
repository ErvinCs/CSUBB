package Repository;

import Domain.Book;
import Domain.Client;
import Domain.Rental;
import Domain.Validators.BookValidator;
import Domain.Validators.ClientValidator;
import Domain.Validators.RentalValidator;
import Domain.Validators.Validator;
import Repository.FileRepository.BookFileRepository;
import Repository.FileRepository.ClientFileRepository;
import Repository.FileRepository.RentalFileRepository;
import org.junit.Test;

import java.awt.image.ReplicateScaleFilter;

public class FileRepoTest {

    private Validator<Book> bookValidator = new BookValidator();
    private Validator<Client> clientValidator = new ClientValidator();
    private Validator<Rental> rentalValidator = new RentalValidator();
    private Repository<Long, Book> bookRepo = new BookFileRepository(bookValidator,"./data/testbooks.txt");
    private Repository<Long, Client> clientRepo = new ClientFileRepository(clientValidator, "./data/testclients.txt");
    private Repository<Long, Rental> rentalRepo = new RentalFileRepository(rentalValidator, "./data/testrentals.txt");

    @Test
    public void testSAveToFile()
    {
        //TODO
    }


}
