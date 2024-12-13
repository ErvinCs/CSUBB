package UI;

import TCP.ClientService;
import UI.View.*;

public class ClientConsole {

    private ClientService handlerService;

    public ClientConsole(ClientService handlerService) {this.handlerService = handlerService;}

    public void runConsole()
    {
        Command exitCommand = new Command("0", "Exit") {
            @Override
            public void execute() {
                System.exit(0);
            }
        };
        BookMenu bookMenu = new BookMenu("1", "Books", handlerService);
        ClientMenu clientMenu = new ClientMenu("2", "Clients", handlerService);
        RentalMenu rentalMenu = new RentalMenu("3", "Rentals", handlerService);

        Menu mainMenu = new Menu();
        mainMenu.addCommand(exitCommand);
        mainMenu.addCommand(bookMenu);
        mainMenu.addCommand(clientMenu);
        mainMenu.addCommand(rentalMenu);
        mainMenu.execute();
    }
}
