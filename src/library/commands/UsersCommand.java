package library.commands;

import library.models.AccessLevel;
import library.repository.FileRepository;

/**
 * Adds and removes users. Admin only.
 */
public class UsersCommand implements Command {
    private FileRepository storage;

    /**
     * @param storage the repository for managing users
     */
    public UsersCommand(FileRepository storage) {
        this.storage = storage;
    }

    /**
     * Runs the add or remove user action.
     * @return result message
     */
    @Override
    public String execute(String[] args) {
        if (!"admin".equals(storage.getLoggedUser())) {
            return "Access denied.";
        }

        if (args.length < 2) {
            return "Invalid command.";
        }

        String action = args[1].toLowerCase();

        if (action.equals("add") && args.length > 2) {
            String[] userData = args[2].split("\\|", -1);
            if (userData.length < 3) return "Error: Incomplete data.";

            try {
                String username = userData[0].trim();
                String password = userData[1].trim();
                AccessLevel role = AccessLevel.valueOf(userData[2].toUpperCase().trim());

                if (storage.userExists(username)) {
                    return "User already exists.";
                }

                storage.addUser(username, password, role);
                return "User " + username + " added.";
            } catch (IllegalArgumentException e) {
                return "Error: Invalid role. Use ADMIN or USER.";
            }
        }

        if (action.equals("remove") && args.length > 2) {
            String username = args[2];
            boolean removed = storage.removeUser(username);
            return removed ? "User removed." : "User not found or cannot be removed.";
        }

        return "Invalid command.";
    }
}