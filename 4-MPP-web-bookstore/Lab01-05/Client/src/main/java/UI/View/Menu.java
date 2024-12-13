package UI.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu extends Command {

    protected Map<String, Command> commands = new HashMap<>();

    public Menu() {}

    public Menu(String key, String description) {super(key, description);}

    public void addCommand(Command c) {commands.put(c.getKey(),c);}

    public void printMenu() {commands.values().stream().forEach(System.out::println);}

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("");
            printMenu();

            System.out.print("\nInsert command: ");
            String key = scanner.nextLine();

            if (key.equals("0")) break;

            Command command = commands.get(key);

            if (command == null) {
                System.out.println("Invalid command.\n");
                continue;
            }

            command.execute();
        }
    }
}
