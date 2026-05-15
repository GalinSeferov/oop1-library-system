package library.commands;

import library.exceptions.InvalidCommandException;
import library.exceptions.NoPermissionException;
import library.repository.FileRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages all commands and figures out which one to run based on user input.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Sets up all the available commands.
     * @param storage the repository passed to commands that need it
     */
    public CommandManager(FileRepository storage) {
        commands.put("help", new HelpCommand());
        commands.put("open", new OpenCommand(storage));
        commands.put("close", new CloseCommand(storage));
        commands.put("save", new SaveCommand(storage));
        commands.put("save as", new SaveAsCommand(storage));
        commands.put("login", new LoginCommand(storage));
        commands.put("logout", new LogoutCommand(storage));
        commands.put("users", new UsersCommand(storage));
        commands.put("books", new BooksCommand(storage));
    }

    /**
     * Takes the user input and runs the right command.
     * @param line the full line the user typed
     * @return the result message from the command
     */
    public String process(String line) {
        String[] parts = line.split(" ", 3);

        if (parts.length == 0 || parts[0].isEmpty()) return "";

        String label = parts[0].toLowerCase();
        if (label.equals("save") && parts.length > 1 && parts[1].equalsIgnoreCase("as")) {
            label = "save as";
            String path = (parts.length > 2) ? parts[2] : "";
            Command saveAsCmd = commands.get("save as");
            if (saveAsCmd != null) {
                return saveAsCmd.execute(new String[]{"save as", path});
            }
        }

        Command command = commands.get(label);
        if (command != null) {
            try {
                return command.execute(parts);
            } catch (NoPermissionException e) {
                return e.getMessage();
            } catch (InvalidCommandException e) {
                return e.getMessage();
            }
        }
        return "Error: Unknown command!";
    }
}