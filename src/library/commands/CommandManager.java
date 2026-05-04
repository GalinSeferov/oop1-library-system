package library.commands;

import library.repository.FileRepository;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

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

    public String process(String line) {
        String[] parts = line.split(" ", 4);

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
            return command.execute(parts);
        }
        return "Error: Unknown command!";
    }
}
