package library.commands;

import library.models.AccessLevel;
import library.repository.FileRepository;
import java.util.Scanner;

/**
 * Handles user input for commands that need multiple fields.
 */
public class InputHandler {
    private FileRepository storage;
    private CommandManager manager;
    private Scanner scanner;

    /**
     * @param storage the repository
     * @param manager the command manager
     * @param scanner the scanner for reading input
     */
    public InputHandler(FileRepository storage, CommandManager manager, Scanner scanner) {
        this.storage = storage;
        this.manager = manager;
        this.scanner = scanner;
    }

    /**
     * Handles the login command.
     * @return result message
     */
    public String handleLogin() {
        if (storage.getCurrentFile() == null) {
            return "Error! Please open a file first!";
        }
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = readPassword();
        return manager.process("login " + user + " " + pass);
    }

    /**
     * Handles adding a new book.
     * @return result message
     */
    public String handleBooksAdd() {
        if (storage.getLoggedUserRole() == null || storage.getLoggedUserRole() != AccessLevel.ADMIN) {
            return "Access denied.";
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
        return manager.process(cmd);
    }

    /**
     * Handles adding a new user.
     * @return result message
     */
    public String handleUsersAdd() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = readPassword();
        System.out.print("Role (ADMIN/USER): ");
        String role = scanner.nextLine();

        String cmd = "users add " + username + "|" + password + "|" + role;
        return manager.process(cmd);
    }

    /**
     * Reads a password without showing it on screen.
     * @return the password
     */
    public String readPassword() {
        try {
            if (System.console() != null) {
                return new String(System.console().readPassword());
            }
        } catch (Exception e) {
        }
        return scanner.nextLine();
    }
}