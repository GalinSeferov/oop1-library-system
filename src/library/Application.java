package library;

import library.commands.CommandManager;
import library.models.AccessLevel;
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

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("exit")) break;

            if (line.equalsIgnoreCase("login")) {
                if (storage.getCurrentFile() == null) {
                    System.out.println("Error! Please open a file first!");
                    continue;
                }
                System.out.print("Enter username: ");
                String user = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = readPassword();
                System.out.println(manager.process("login " + user + " " + pass));
            }
            else if (line.equalsIgnoreCase("books add")) {
                if (storage.getLoggedUserRole() == null || storage.getLoggedUserRole() != AccessLevel.ADMIN) {
                    System.out.println("Access denied.");
                    continue;
                }
                System.out.print("Author: ");
                String author = scanner.nextLine().trim();
                System.out.print("Title: ");
                String title = scanner.nextLine().trim();
                System.out.print("Genre (FANTASY, THRILLER, CLASSIC, SCIFI, HORROR, BIOGRAPHY, OTHER): ");
                String genre = scanner.nextLine().trim().toUpperCase();
                System.out.print("Description: ");
                String desc = scanner.nextLine().trim();
                System.out.print("Year: ");
                String year = scanner.nextLine().trim();
                System.out.print("Keywords: ");
                String tags = scanner.nextLine().trim();
                System.out.print("Rating: ");
                String rating = scanner.nextLine().trim();
                System.out.print("ID: ");
                String id = scanner.nextLine().trim();

                String cmd = "books add " + author + "|" + title + "|" + genre + "|" + desc + "|" + year + "|" + tags + "|" + rating + "|" + id;
                System.out.println(manager.process(cmd));
            }
            else if (line.equalsIgnoreCase("users add")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = readPassword();
                System.out.print("Role (ADMIN/USER): ");
                String role = scanner.nextLine();

                String cmd = "users add " + username + "|" + password + "|" + role;
                System.out.println(manager.process(cmd));
            }
            else {
                System.out.println(manager.process(line));
            }
        }
        scanner.close();
    }
    /**
     * Reads a password without showing it on screen.
     * @return the password the user typed
     */
    private static String readPassword() {
        try {
            if (System.console() != null) {
                return new String(System.console().readPassword());
            }
        } catch (Exception e) {
        }
        return new Scanner(System.in).nextLine();
    }
}