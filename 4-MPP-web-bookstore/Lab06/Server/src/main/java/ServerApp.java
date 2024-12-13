
import Service.BookService;
import Service.ClientService;
import Service.RentalService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerApp {

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");

        BookService bookService = context.getBean(BookService.class);
        ClientService clientService = context.getBean(ClientService.class);
        RentalService rentalService = context.getBean(RentalService.class);

//        try {
//            BookService bookStub = (BookService) UnicastRemoteObject.exportObject(bookService, 0);
//            ClientService clientStub = (ClientService) UnicastRemoteObject.exportObject(clientService, 0);
//            RentalService rentalStub = (RentalService) UnicastRemoteObject.exportObject(rentalService, 0);
//            Registry registry = LocateRegistry.getRegistry();
//            registry.bind("Books", bookStub);
//            registry.bind("Clients", clientStub);
//            registry.bind("Rentals", rentalStub);
//        } catch (RemoteException | AlreadyBoundException ex) {
//            System.out.println("ServerApp Exception: ");
//            ex.printStackTrace();
//        }

        System.out.println("Server -> ready");
    }
}
