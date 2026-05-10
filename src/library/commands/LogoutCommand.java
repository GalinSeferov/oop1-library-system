package library.commands;

import library.repository.FileRepository;

/**
 * Command for logging out of the system.
 */
public class LogoutCommand implements Command {
    private FileRepository storage;

    public LogoutCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        if (storage.getLoggedUser() == null) {
            return "You are not logged in!";
        }
        storage.logout();
        return "Successfully logged out.";
    }
}