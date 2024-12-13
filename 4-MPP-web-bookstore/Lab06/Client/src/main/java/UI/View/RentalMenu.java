package UI.View;


import Service.RentalService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class RentalMenu{

    private RentalService rentalService;

    public RentalMenu(RentalService rentalService)
    {
        this.rentalService = rentalService;
    }

    public void rentalMenu()
    {
        while(true)
        {
            int cmd = -1;
            System.out.println(
                    "Choose option: " + System.lineSeparator()+
                            "1 - Add rental" + System.lineSeparator()+
                            "2 - Delete rental" + System.lineSeparator()+
                            "3 - Update rental" + System.lineSeparator()+
                            "4 - Display rental" + System.lineSeparator()+
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
                    rentBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    updateRental();
                    break;
                case 4:
                    displayRentals();
                    break;
            }
        }
    }


    public void rentBook() {
        System.out.println("Enter rentalID, bookID and clientID");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            Long bookid = Long.parseLong(bufferRead.readLine());
            Long clientid = Long.parseLong(bufferRead.readLine());

            String rental = id.toString() + "," + bookid.toString() + "," + clientid.toString();
            this.rentalService.add(rental);

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

            this.rentalService.delete(id);

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

            String rental = id.toString() + "," + bookid.toString() + "," + clientid.toString();

            this.rentalService.update(rental);
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayRentals()
    {
        try {
            this.rentalService.findAll().forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
