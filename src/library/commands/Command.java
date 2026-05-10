package library.commands;

/**
 * The interface for all commands in the library system.
 */
public interface Command {

    /**
     * Executes the command.
     * @param args the arguments typed by the user.
     * @return the result of the command as text.
     */
    String execute(String[] args);
}