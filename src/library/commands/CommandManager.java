package library.commands;

import library.repository.FileRepository;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        FileRepository storage = new FileRepository();
        commands.put("help", new HelpCommand());
        commands.put("open", new OpenCommand(storage));
        commands.put("close", new CloseCommand(storage));
        commands.put("save", new SaveCommand(storage));
        commands.put("save as", new SaveAsCommand(storage));
    }

    public String process(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length == 0) return "";

        String label = parts[0].toLowerCase();

        if (label.equals("save") && parts.length > 1 && parts[1].equalsIgnoreCase("as")) {
            label = "save as";
            String[] newArgs = new String[parts.length - 1];
            newArgs[0] = "save as";
            for (int i = 2; i < parts.length; i++) {
                newArgs[i-1] = parts[i];
            }
            parts = newArgs;
        }

        Command command = commands.get(label);
        if (command != null) {
            return command.execute(parts);
        }
        return "Error: Unknown command!";
    }
}