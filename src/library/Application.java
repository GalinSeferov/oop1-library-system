package library;

import library.commands.CommandManager;
import library.repository.FileRepository;
import java.util.Scanner;

public class Application {
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
                System.out.print("Enter username: ");
                String user = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();
                for (int i = 0; i < pass.length(); i++) System.out.print("*");
                System.out.println();
                System.out.println(manager.process("login " + user + " " + pass));
            }
            else if (line.equalsIgnoreCase("books add")) {
                System.out.print("Author: ");
                String author = scanner.nextLine();
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Genre: ");
                String genre = scanner.nextLine();
                System.out.print("Description: ");
                String desc = scanner.nextLine();
                System.out.print("Year: ");
                String year = scanner.nextLine();
                System.out.print("Keywords: ");
                String tags = scanner.nextLine();
                System.out.print("Rating: ");
                String rating = scanner.nextLine();
                System.out.print("ID: ");
                String id = scanner.nextLine();

                String cmd = "books add " + author + "|" + title + "|" + genre + "|" + desc + "|" + year + "|" + tags + "|" + rating + "|" + id;
                System.out.println(manager.process(cmd));
            }
            else {
                System.out.println(manager.process(line));
            }
        }
        scanner.close();
    }
}