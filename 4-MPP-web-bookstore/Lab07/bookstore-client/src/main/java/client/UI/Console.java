package client.UI;

import core.Domain.Client;
import core.Domain.Rental;
import core.Service.BookServiceImpl;
import core.Validators.BookstoreException;
import core.Validators.ValidatorException;
import core.Service.ClientService;
import core.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import core.Domain.Book;
import core.Service.BookService;
import org.springframework.web.client.RestTemplate;
import web.dto.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Console
{
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("client.Configuration");
    RestTemplate restTemplate = context.getBean(RestTemplate.class);

    public void runConsole() {
        while (true) {
            System.out.println("----------------------");
            System.out.println("1 - Add a book ");
            System.out.println("2 - Print all books ");
            System.out.println("3 - Delete a book ");
            System.out.println("4 - Update a book ");
            System.out.println("5 - Add a client ");
            System.out.println("6 - Print all clients ");
            System.out.println("7 - Delete a client ");
            System.out.println("8 - Update a client ");
            System.out.println("9 - Add a rental ");
            System.out.println("10 - Print all rentals ");
            System.out.println("11 - Delete a rental ");
            System.out.println("12 - Update a rental ");
            System.out.println("0 - Exit");
            System.out.println("----------------------\n");

            Scanner scanner = new Scanner(System.in);

            System.out.println("\nEnter command: ");
            int cmd;
            cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    printAllBooks();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    addClient();
                    break;
                case 6:
                    printAllClients();
                    break;
                case 7:
                    deleteClient();
                    break;
                case 8:
                    updateClient();
                    break;
                case 9:
                    addRental();
                    break;
                case 10:
                    printAllRentals();
                    break;
                case 11:
                    deleteRental();
                    break;
                case 12:
                    updateRental();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }

    }

    private void printAllBooks()
    {
        BooksDto booksDto = restTemplate
                .getForObject("http://localhost:8080/api/books", BooksDto.class);
        booksDto.getBooks()
                .forEach(System.out::println);
    }

    private void addBooks()
    {
        System.out.println("Book: Name, Author, ISBN");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        try{
            String title = bufferRead.readLine();
            String author = bufferRead.readLine();

            while(!scan.hasNextInt()) {
                scan.next();
            }
            int isbn = scan.nextInt();

            BookDto bookDto = restTemplate.postForObject("http://localhost:8080/api/books",
                            new BookDto(title, author, isbn), BookDto.class);
            //System.out.println(bookDto.toString());
        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteBook()
    {
        System.out.println("Enter the book's id that you want to delete:");
        Scanner scan = new Scanner(System.in);
        try {
            while (!scan.hasNextLong()) {
                scan.next();
            }
            Long bookId = scan.nextLong();

            restTemplate.delete("http://localhost:8080/api/books/{bookId}",
                            bookId);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    private void updateBook()
    {
        System.out.println("Enter the book's id that you want to update:");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        try{
            while(!scan.hasNextLong()) {
                scan.next();
            }
            Long bookId = scan.nextLong();
            System.out.println("Book: Name, Author, ISBN");
            String title = bufferRead.readLine();
            String author = bufferRead.readLine();

            while(!scan.hasNextInt()) {
                scan.next();
            }
            int isbn = scan.nextInt();

            BookDto bookDto = new BookDto(title, author, isbn);
            System.out.println(bookDto.toString());

            restTemplate.put("http://localhost:8080/api/books/{bookId}",
                    bookDto, bookId);

        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    //--------------------------------------------------------------------

    private void printAllClients()
    {
        ClientsDto clientsDto = restTemplate
                .getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        clientsDto.getClients()
                .forEach(System.out::println);
    }

    private void addClient()
    {
        System.out.println("Client: Name, Country");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferRead.readLine();
            String country = bufferRead.readLine();

            ClientDto clientDto = restTemplate.postForObject("http://localhost:8080/api/clients",
                                new ClientDto(name, country), ClientDto.class);

            //System.out.println(clientDto.toString());
        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteClient()
    {
        System.out.println("Enter the client's id that you want to delete:");
        Scanner scan = new Scanner(System.in);
        try {
            while(!scan.hasNextLong()) {
                scan.next();
            }
            Long clientId = scan.nextLong();

            restTemplate.delete("http://localhost:8080/api/clients/{clientId}", clientId);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }

    private void updateClient() {
        System.out.println("Enter the client's id that you want to update:");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        Long clientId = scan.nextLong();
        if (clientId <= 0)
            System.out.println("Invalid id!");
        try {
            System.out.println("Client: Name, Country");

            try {
                String name = bufferRead.readLine();
                String country = bufferRead.readLine();

                ClientDto clientDto = new ClientDto(name, country);
                System.out.println(clientDto.toString());

                restTemplate.put("http://localhost:8080/api/clients/{clientId}",
                        clientDto, clientId);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------

    private void printAllRentals()
    {
        RentalsDto rentalsDto = restTemplate
                .getForObject("http://localhost:8080/api/rentals", RentalsDto.class);
        rentalsDto.getRentals()
                .forEach(System.out::println);
    }

    private void addRental() {
        System.out.println("Rental: bookID, clientID");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        ClientsDto clientsDto = restTemplate
                .getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        Set<ClientDto> c = clientsDto.getClients();
        BooksDto booksDto = restTemplate
                .getForObject("http://localhost:8080/api/books", BooksDto.class);
        Set<BookDto> b = booksDto.getBooks();

        try {
            Long bookID = Long.valueOf(bufferRead.readLine());
            Long clientID = Long.valueOf(bufferRead.readLine());

            b.forEach(book ->
            {
                if (book.getId() == bookID)
                {
                    c.forEach(client ->
                    {
                        if (client.getId() == clientID)
                        {
                            RentalDto rentalDto = restTemplate.postForObject("http://localhost:8080/api/rentals",
                                    new RentalDto(bookID, clientID), RentalDto.class);
                            return;
                        }

                    });
                }
            });


        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteRental()
    {
        System.out.println("Enter the rental's id that you want to delete:");
        Scanner scan = new Scanner(System.in);
        try {
            while(!scan.hasNextLong()) {
                scan.next();
            }
            Long rentalId = scan.nextLong();

            restTemplate.delete("http://localhost:8080/api/rentals/{rentalId}", rentalId);
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

    }

    private void updateRental() {
        System.out.println("Enter the rental's id that you want to update:");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);

        ClientsDto clientsDto = restTemplate
                .getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        Set<ClientDto> c = clientsDto.getClients();
        BooksDto booksDto = restTemplate
                .getForObject("http://localhost:8080/api/books", BooksDto.class);
        Set<BookDto> b = booksDto.getBooks();

        Long rentalId = scan.nextLong();
        if (rentalId <= 0)
            System.out.println("Invalid id!");
        try {
            System.out.println("Rental: bookID, clientID");

            try {
                Long bookID = Long.valueOf(bufferRead.readLine());
                Long clientID = Long.valueOf(bufferRead.readLine());

                b.forEach(book ->
                {
                    if (book.getId() == bookID)
                    {
                        c.forEach(client ->
                        {
                            if (client.getId() == clientID)
                            {
                                RentalDto rentalDto = new RentalDto(bookID, clientID);
                                System.out.println(rentalDto.toString());

                                restTemplate.put("http://localhost:8080/api/rentals/{rentalId}",
                                        rentalDto, rentalId);
                                return;
                            }

                        });
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ValidatorException e) {
            e.printStackTrace();
        }
    }

}


