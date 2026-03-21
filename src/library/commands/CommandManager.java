package library.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        commands.put("help", new HelpCommand());

        // open, login...
    }

    public String process(String line) {
        String[] parts = line.split("\\s+");
        String label = parts[0].toLowerCase();

        Command command = commands.get(label);
        if (command != null) {
            return command.execute(parts);
        }
        return "Error: Unknown command!";
    }
}