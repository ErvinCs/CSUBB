package UI.View;

import TCP.ClientService;

public class ClientMenu extends Menu {

    private ClientService handlerservice;

    public ClientMenu(String key, String description, ClientService handlerservice)
    {
        super(key, description);
        this.handlerservice = handlerservice;

        addCommand(new ClientCommand("1", "Add client", handlerservice));
        addCommand(new ClientCommand("2", "Delete client", handlerservice));
        addCommand(new ClientCommand("3", "Update client", handlerservice));
        addCommand(new ClientCommand("4", "Display clients", handlerservice));
        addCommand(new ClientCommand("5", "Filter clients", handlerservice));
        addCommand(new ClientCommand("0", "Back", handlerservice));

    }
}
