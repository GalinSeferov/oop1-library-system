package library.commands;

import library.repository.FileRepository;

import java.util.Scanner;

public class LoginCommand implements Command{
    private FileRepository storage;

    public LoginCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        if (storage.getCurrentFile() == null) {
            return "Error! Please open a file first!";
        }
        if (storage.getLoggedUser() != null) {
            return "You are already logged in.";
        }
        if (args.length < 3) {
            return "Error! Usage is login <username> <password>";
        }

        String user = args[1];
        String pass = args[2];

        if (user.equals("admin") && pass.equals("i<3Java")) {
            storage.login(user);
            return "Welcome, " + user + "!";
        }

        return "Error! Invalid username or password!";
    }
}
