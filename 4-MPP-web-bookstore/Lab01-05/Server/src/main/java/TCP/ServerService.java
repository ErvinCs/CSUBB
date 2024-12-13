package TCP;

import Controller.BookController;
import Controller.ClientController;
import Controller.RentalController;
import Domain.Book;
import Domain.Client;
import Domain.Rental;
import Repository.DBRepository.RentalDBRepository;
import Service.Message;
import org.w3c.dom.ls.LSException;

import java.net.ServerSocket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class ServerService {

    private ClientController clientController;
    private BookController bookController;
    private RentalController rentalController;

    private Map<String, UnaryOperator<Message>> methodhandlers;

    public ServerService(ClientController clientController, BookController bookController, RentalController rentalController)
    {
        this.clientController = clientController;
        this.bookController = bookController;
        this.rentalController = rentalController;

        methodhandlers = new HashMap<>();
        initializeMethodHandlers();
    }

    public UnaryOperator<Message> getHandler(String method) {return methodhandlers.get(method);}

    public void addHandler(String methodName, UnaryOperator<Message> handler)
    {
        methodhandlers.put(methodName, handler);
    }

    public void initializeMethodHandlers()
    {

        //----Book handlers----

        addHandler("addBook", (request)->{
            List<String> args = Arrays.asList(request.getBody().split(";"));
            Long id = Long.parseLong(args.get(0));
            String name = args.get(1);
            String author = args.get(2);
            int isbn = Integer.parseInt(args.get(3));
            Book b = new Book(name, author, isbn);
            b.setID(id);
            bookController.add(b);
            return Message.builder().header("Book added.").body("").build();
        });

        addHandler("deleteBook", (request)->{
            Long id = Long.parseLong(request.getBody());
            bookController.delete(id);
            return Message.builder().header("Book deleted.").body("").build();
        });

        addHandler("updateBook", (request)->{
            List<String> args = Arrays.asList(request.getBody().split(";"));
            Long id = Long.parseLong(args.get(0));
            String name = args.get(1);
            String author = args.get(2);
            int isbn = Integer.parseInt(args.get(3));
            Book b = new Book(name, author, isbn);
            b.setID(id);
            bookController.update(b);

            return Message.builder().header("Book updated.").body("").build();
        });

        addHandler("displayBooks", (request)->{
            String books = "BOOKS:;";
            books += bookController.getAll().stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Books displayed.").body(books).build();
        });

        addHandler("filterBooks", (request) -> {
            String books = "Books containing the string \\\"%s\\\" in their name:;";
            books += bookController.filter(request.getBody()).stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Books filtered.").body(books).build();
        });


        //----Client handlers----

        addHandler("addClient", (request)->{
            List<String> args = Arrays.asList(request.getBody().split(";"));
            Long id = Long.parseLong(args.get(0));
            String name = args.get(1);
            String country = args.get(2);
            Client c = new Client(name, country);
            c.setID(id);
            clientController.add(c);
            return Message.builder().header("Client added.").body("").build();
        });

        addHandler("deleteClient", (request)->{
            Long id = Long.parseLong(request.getBody());
            clientController.delete(id);
            return Message.builder().header("Client deleted.").body("").build();
        });

        addHandler("updateClient", (request)->{
            List<String> args = Arrays.asList(request.getBody().split(";"));
            Long id = Long.parseLong(args.get(0));
            String name = args.get(1);
            String country = args.get(2);
            Client c = new Client(name, country);
            c.setID(id);
            clientController.update(c);
            return Message.builder().header("Client updated.").body("").build();
        });

        addHandler("displayClients", (request)->{
            String clients = "CLIENTS:;";
            clients += clientController.getAll().stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Clients displayed.").body(clients).build();
        });

        addHandler("filterClients", (request) -> {
            String clients = "Clients having the name \\\"%s\\\":;";
            clients += clientController.filter(request.getBody()).stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Clients filtered.").body(clients).build();
        });

        //---Rental handlers---

        addHandler("rentBook", (request)->{
            List<String> args = Arrays.asList(request.getBody().split(";"));
            Long id = Long.parseLong(args.get(0));
            Long bookId = Long.parseLong(args.get(1));
            Long clId = Long.parseLong(args.get(2));
            Rental r = new Rental(bookId, clId);
            r.setID(id);
            rentalController.add(r);
            return Message.builder().header("Book rented.").body("").build();
        });

        addHandler("returnBook", (request)->{
            Long id = Long.parseLong(request.getBody());
            rentalController.delete(id);
            return Message.builder().header("Book returned.").body("").build();
        });

        addHandler("displayRentals", (request)->{
            String rentals = "RENTALS:;";
            rentals += rentalController.getAll().stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Rentals displayed.").body(rentals).build();
        });

        addHandler("filterRentals", (request) -> {
            String rentals  = "Rentals having the id \\\"%s\\\":;";
            rentals += rentalController.filter(request.getBody()).stream().map(Object::toString).reduce("", (s, m) -> s + m + ";");
            return Message.builder().header("Rentals filtered.").body(rentals).build();
        });
    }
}
