package UI.View;

import Service.Message;
import TCP.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class RentalCommand extends Command {

    private ClientService handlerService;

    public RentalCommand(String key, String description, ClientService handlerService) {
        super(key, description);
        this.handlerService = handlerService;
    }

    public void rentBook() {
        System.out.println("Enter rentalID, bookID and clientID");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            Long bookid = Long.parseLong(bufferRead.readLine());
            Long clientid = Long.parseLong(bufferRead.readLine());

            Message request = Message.builder().header("rentBook").body("" + id + ";" + bookid + ";" + clientid).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        } catch (IOException | NumberFormatException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void returnBook()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the rental's id that you want to delete:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            Message request = Message.builder().header("returnBook").body("" + id).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void updateRental()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the rental's id that you want to update:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            System.out.println("Enter rental's bookID and clientID");
            Long bookid = Long.parseLong(reader.readLine());
            Long clientid = Long.parseLong(reader.readLine());

            Message request = Message.builder().header("updateRental").body("" + id + ";" + bookid + ";" + clientid).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayRentals()
    {
        Message request = Message.builder()
                .header("displayRentals")
                .body("")
                .build();
        CompletableFuture<Message> response = handlerService.processMessage(request);
        response.thenAccept(m -> Arrays.asList(m.getBody().split(";")).forEach(System.out::println));
    }

    public void filterRentals()
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nGive id: ");
            Long id = Long.parseLong(reader.readLine());

            Message request = Message.builder()
                    .header("filterRentals")
                    .body("" + id)
                    .build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> Arrays.asList(m.getBody().split(";")).forEach(System.out::println));
        } catch (IOException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    @Override
    public void execute() {
        switch (key) {
            case "1":
                rentBook();
                break;
            case "2":
                returnBook();
                break;
            case "3":
                updateRental();
                break;
            case "4":
                displayRentals();
                break;
            case "5":
                filterRentals();
                break;
        }
    }
}
