package library.commands;

import library.repository.FileRepository;

/**
 * Command to save the current data into a new file.
 */

public class SaveAsCommand implements Command {
    private FileRepository storage;
    /**
     * @param storage the repository used to save the file
     */
    public SaveAsCommand(FileRepository storage) {
        this.storage = storage;
    }
    /**
     * Saves the file under a new name.
     * @param args the new file path
     * @return result message
     */
    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "Error! Please specify a new file path.";
        }
        return storage.saveAs(args[1]);
    }
}