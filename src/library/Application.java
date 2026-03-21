package library;

import library.commands.CommandManager;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandManager manager = new CommandManager();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (!input.isEmpty()) {
                String response = manager.process(input);
                System.out.println(response);
            }
        }
    }
}