package library.commands;

import library.repository.FileRepository;

public class UsersCommand implements Command {
    private FileRepository storage;

    public UsersCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        if (!"admin".equals(storage.getLoggedUser())) {
            return "Access denied.";
        }

        String action = args[1].toLowerCase();

        if (action.equals("add") && args.length > 2) {
            storage.addUser(args[2]);
            return "User " + args[2] + " added.";
        }

        if (action.equals("remove") && args.length > 2) {
            boolean removed = storage.removeUser(args[2]);
            return removed ? "User removed." : "User not found or cannot be removed.";
        }

        return "Invalid command.";
    }
}