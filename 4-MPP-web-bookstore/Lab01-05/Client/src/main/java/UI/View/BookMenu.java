package UI.View;

import TCP.ClientService;

public class BookMenu extends Menu {

    private ClientService handlerservice;

    public BookMenu(String key, String description, ClientService handlerservice)
    {
        super(key, description);
        this.handlerservice = handlerservice;

        addCommand(new BookCommand("1", "Add book", handlerservice));
        addCommand(new BookCommand("2", "Delete book", handlerservice));
        addCommand(new BookCommand("3", "Update book", handlerservice));
        addCommand(new BookCommand("4", "Display books", handlerservice));
        addCommand(new BookCommand("5", "Filter books", handlerservice));
        addCommand(new BookCommand("0", "Back", handlerservice));
    }
}
