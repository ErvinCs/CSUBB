package UI.View;

import Domain.Book;
import Service.Message;
import TCP.ClientService;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class BookCommand extends Command {

    private ClientService handlerservice;

    public BookCommand(String key, String description, ClientService handlerservice) {
        super(key, description);
        this.handlerservice = handlerservice;
    }

    public void addBook() {
        System.out.println("Enter bookID, Name, Author, ISBN");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String author = bufferRead.readLine();
            int ISBN = Integer.parseInt(bufferRead.readLine());

            Message request = Message.builder().header("addBook").body("" + id + ";" + name + ";" + author + ";" + ISBN).build();
            CompletableFuture<Message> response = handlerservice.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        } catch (IOException | NumberFormatException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void deleteBook()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the book's id that you want to delete:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            Message request = Message.builder().header("deleteBook").body("" + id).build();
            CompletableFuture<Message> response = handlerservice.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void updateBook()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the book's id that you want to update:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            System.out.println("Enter book Name, Author, ISBN");
            String name = reader.readLine();
            String author = reader.readLine();
            int ISBN = Integer.parseInt(reader.readLine());

            Message request = Message.builder().header("updateBook").body("" + id + ";" + name + ";" + author + ";" + ISBN).build();
            CompletableFuture<Message> response = handlerservice.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayBooks()
    {
        Message request = Message.builder()
                .header("displayBooks")
                .body("")
                .build();
        CompletableFuture<Message> response = handlerservice.processMessage(request);
        response.thenAccept(m -> Arrays.asList(m.getBody().split(";")).forEach(System.out::println));
    }

    public void filterBooks()
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nGive name: ");
            String string = reader.readLine();

            Message request = Message.builder()
                    .header("filterBooks")
                    .body(string)
                    .build();
            CompletableFuture<Message> response = handlerservice.processMessage(request);
            response.thenAccept(m -> Arrays.asList(m.getBody().split(";")).forEach(System.out::println));
        } catch (IOException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    @Override
    public void execute() {
        switch (key)
        {
            case "1":
                addBook();
                break;
            case "2":
                deleteBook();
                break;
            case "3":
                updateBook();
                break;
            case "4":
                displayBooks();
                break;
            case "5":
                filterBooks();
                break;
        }
    }
}
