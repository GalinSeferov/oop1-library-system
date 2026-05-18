package library.commands;

import library.repository.FileRepository;

/**
 * Command to save changes to the currently open file.
 */

public class SaveCommand implements Command {
    private FileRepository storage;
    /**
     * @param storage the repository used to save the file
     */
    public SaveCommand(FileRepository storage) {
        this.storage = storage;
    }
    /**
     * Saves the current file.
     * @return result message
     */
    @Override
    public String execute(String[] args) {
        return storage.saveFile();
    }
}