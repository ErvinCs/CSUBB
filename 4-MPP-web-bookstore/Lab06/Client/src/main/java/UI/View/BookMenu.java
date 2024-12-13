package UI.View;


import Service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class BookMenu{

    private BookService bookService;

    public BookMenu(BookService bookService)
    {
        this.bookService = bookService;
    }


    public void bookMenu()
    {
        while(true)
        {
            int cmd = -1;
            System.out.println(
                    "Choose option: " + System.lineSeparator()+
                            "1 - Add book" + System.lineSeparator()+
                            "2 - Delete book" + System.lineSeparator()+
                            "3 - Update book" + System.lineSeparator()+
                            "4 - Display books" + System.lineSeparator()+
                            "0 - Back"
            );

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try{
                cmd = Integer.valueOf(bufferedReader.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            switch (cmd)
            {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    displayBooks();
                    break;
            }
        }
    }

    public void addBook() {
        System.out.println("Enter bookID, Name, Author, ISBN");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String author = bufferRead.readLine();
            Integer ISBN = Integer.parseInt(bufferRead.readLine());

            String book = id.toString() + "," + name + "," + author + "," + ISBN.toString();
            this.bookService.add(book);

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

            this.bookService.delete(id);

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
            Integer ISBN = Integer.parseInt(reader.readLine());

            String book = id.toString() + "," + name + "," + author + "," + ISBN.toString();
            this.bookService.update(book);
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayBooks()
    {
        try {
            this.bookService.findAll().forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
