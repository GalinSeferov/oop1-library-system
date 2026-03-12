package library;

import library.models.AccessLevel;
import library.models.User;

import java.util.Scanner;

public class Application {
    private static final User DEFAULT_ADMIN = new User("admin", "i<3Java",AccessLevel.ADMIN);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Library System Initialized.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system...");
                break;
            }
            //komandite za terminala
            System.out.println("Command received: " + input);
        }
    }
}
