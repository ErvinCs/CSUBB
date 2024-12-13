package UI.View;


import Service.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;


public class ClientMenu{

    private ClientService clientService;

    public ClientMenu(ClientService clientService)
    {
        this.clientService = clientService;
    }

    public void clientMenu()
    {
        while(true)
        {
            int cmd = -1;
            System.out.println(
                    "Choose option: " + System.lineSeparator()+
                            "1 - Add client" + System.lineSeparator()+
                            "2 - Delete client" + System.lineSeparator()+
                            "3 - Update client" + System.lineSeparator()+
                            "4 - Display client" + System.lineSeparator()+
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
                    addClient();
                    break;
                case 2:
                    deleteClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    displayClients();
                    break;
            }
        }
    }

    public void addClient() {
        System.out.println("Enter clientID, Name, Country");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String country = bufferRead.readLine();

            String client = id.toString() + "," + name + "," + country;
            this.clientService.add(client);

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

            this.clientService.delete(id);

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

            String client = id.toString() + "," + name + "," + country;
            this.clientService.update(client);
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.println("\n" + e.getMessage());
        }
    }

    public void displayClients()
    {
        try {
            this.clientService.findAll().forEach(System.out::println);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
