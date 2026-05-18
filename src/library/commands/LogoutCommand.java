package library.commands;

import library.repository.FileRepository;

/**
 * Command for logging out of the system.
 */
public class LogoutCommand implements Command {
    private FileRepository storage;

    /**
     * @param storage the repository used to check credentials
     */
    public LogoutCommand(FileRepository storage) {
        this.storage = storage;
    }

    /**
     * Logs out the current user.
     * @return result message
     */
    @Override
    public String execute(String[] args) {
        if (storage.getLoggedUser() == null) {
            return "You are not logged in!";
        }
        storage.logout();
        return "Successfully logged out.";
    }
}