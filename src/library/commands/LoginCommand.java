package library.commands;

import library.repository.FileRepository;

public class LoginCommand implements Command {
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
            return "Login requires username and password.";
        }

        String user = args[1];
        String pass = args[2];

        if (storage.authenticate(user, pass)) {
            storage.login(user);
            return "Welcome, " + user + "!";
        }

        return "Error: Invalid username or password.";
    }
}