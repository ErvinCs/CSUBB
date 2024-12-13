package UI.View;

import Service.Message;
import TCP.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ClientCommand extends Command{

    private ClientService handlerService;

    public ClientCommand(String key, String description, ClientService handlerService) {
        super(key, description);
        this.handlerService = handlerService;
    }

    public void addClient() {
        System.out.println("Enter clientID, Name, Country");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String country = bufferRead.readLine();

            Message request = Message.builder().header("addClient").body("" + id + ";" + name + ";" + country).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        } catch (IOException | NumberFormatException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void deleteClient()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the client's id that you want to delete:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            Message request = Message.builder().header("deleteClient").body("" + id).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));

        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void updateClient()
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the client's id that you want to update:");
            Long id = Long.parseLong(reader.readLine());
            if (id <= 0)
                System.out.println("Invalid id!");

            System.out.println("Enter client Name and Country");
            String name = reader.readLine();
            String country = reader.readLine();

            Message request = Message.builder().header("updateClient").body("" + id + ";" + name + ";" + country).build();
            CompletableFuture<Message> response = handlerService.processMessage(request);
            response.thenAccept(m -> System.out.println(m.getBody()));
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayClients()
    {
        Message request = Message.builder()
                .header("displayClients")
                .body("")
                .build();
        CompletableFuture<Message> response = handlerService.processMessage(request);
        response.thenAccept(m -> Arrays.asList(m.getBody().split(";")).forEach(System.out::println));
    }

    public void filterClients()
    {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nGive name: ");
            String string = reader.readLine();

            Message request = Message.builder()
                    .header("filterClients")
                    .body(string)
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
                addClient();
                break;
            case "2":
                deleteClient();
                break;
            case "3":
                updateClient();
                break;
            case "4":
                displayClients();
                break;
            case "5":
                filterClients();
                break;
        }
    }
}
