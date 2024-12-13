package UI;

import Service.BookService;
import Service.ClientService;
import Service.RentalService;
import UI.View.*;

public class ClientConsole {

    private BookService bookService;
    private ClientService clientService;
    private RentalService rentalService;

    public ClientConsole(BookService bookService, ClientService clientService, RentalService rentalService)
    {
        this.bookService = bookService;
        this.clientService = clientService;
        this.rentalService = rentalService;
    }

    public void run()
    {
        BookMenu bookMenu = new BookMenu(bookService);
        ClientMenu clientMenu = new ClientMenu(clientService);
        RentalMenu rentalMenu = new RentalMenu(rentalService);

        bookMenu.bookMenu();
        clientMenu.clientMenu();
        rentalMenu.rentalMenu();
    }

}
