package library;

import library.commands.CommandManager;
import library.commands.InputHandler;
import library.repository.FileRepository;
import java.util.Scanner;

/**
 * The main class of the library system.
 */
public class Application {

    /**
     * Starts the program and waits for user input.
     */
    public static void main(String[] args) {
        FileRepository storage = new FileRepository();
        CommandManager manager = new CommandManager(storage);
        Scanner scanner = new Scanner(System.in);
        InputHandler handler = new InputHandler(storage, manager, scanner);

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("exit")) break;

            if (line.equalsIgnoreCase("login")) System.out.println(handler.handleLogin());
            else if (line.equalsIgnoreCase("books add")) System.out.println(handler.handleBooksAdd());
            else if (line.equalsIgnoreCase("users add")) System.out.println(handler.handleUsersAdd());
            else System.out.println(manager.process(line));
        }
        scanner.close();
    }
}