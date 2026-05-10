package library.commands;

import library.repository.FileRepository;

/**
 * Command to save changes to the currently open file.
 */

public class SaveCommand implements Command {
    private FileRepository storage;

    public SaveCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        return storage.saveFile();
    }
}