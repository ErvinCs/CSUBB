import Controller.BookController;
import Controller.ClientController;
import Controller.RentalController;
import Domain.Book;
import Domain.Client;
import Domain.Rental;
import Domain.Validators.BookValidator;
import Domain.Validators.ClientValidator;
import Domain.Validators.RentalValidator;
import Repository.DBRepository.BookDBRepository;
import Repository.DBRepository.ClientDBRepository;
import Repository.DBRepository.RentalDBRepository;
import Repository.Repository;
import Service.HandlerService;
import TCP.ServerService;
import TCP.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {

    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String user = "postgres";
    private static final String password = "brewcrew";
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        BookValidator bookValidator = new BookValidator();
        BookDBRepository<Long, Book> bookRepository = new BookDBRepository<>(bookValidator, URL, user, password);
        BookController bookController = new BookController(bookRepository);

        ClientValidator clientValidator = new ClientValidator();
        ClientDBRepository<Long, Client> clientRepository = new ClientDBRepository<>(clientValidator, URL, user, password);
        ClientController clientController = new ClientController(clientRepository);

        RentalValidator rentalValidator = new RentalValidator();
        RentalDBRepository<Long, Rental> rentalRepository = new RentalDBRepository<>(rentalValidator, URL, user, password);
        RentalController rentalController = new RentalController(rentalRepository);

        ServerService service = new ServerService(clientController, bookController, rentalController);
        TcpServer tcpServer = new TcpServer(executorService, service, HandlerService.SERVER_HOST, HandlerService.SERVER_PORT);

        tcpServer.startServer();

        System.out.println("Server -> out");
    }
}
