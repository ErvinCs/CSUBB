
import Service.BookService;
import Service.ClientService;
import Service.RentalService;
import UI.ClientConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientApp {
    public static void main(String args[])
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");

        BookService bookService = (BookService) context.getBean("bookServiceClient");
        ClientService clientService = (ClientService) context.getBean("clientServiceClient");
        RentalService rentalService = (RentalService) context.getBean("rentalServiceClient");

        ClientConsole console = new ClientConsole(bookService, clientService, rentalService);
        console.run();


        System.out.println("Client - out");
    }
}

