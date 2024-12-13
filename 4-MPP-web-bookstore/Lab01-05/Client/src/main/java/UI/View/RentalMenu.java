package UI.View;

import TCP.ClientService;

public class RentalMenu extends Menu {

    private ClientService handlerservice;

    public RentalMenu(String key, String description, ClientService handlerservice)
    {
        super(key, description);
        this.handlerservice = handlerservice;

        addCommand(new RentalCommand("1", "Rent a book", handlerservice));
        addCommand(new RentalCommand("2", "Return book", handlerservice));
        addCommand(new RentalCommand("3", "Update rental", handlerservice));
        addCommand(new RentalCommand("4", "Display rentals", handlerservice));
        addCommand(new RentalCommand("5", "Filter rentals", handlerservice));
        addCommand(new RentalCommand("0", "Back", handlerservice));



    }
}
