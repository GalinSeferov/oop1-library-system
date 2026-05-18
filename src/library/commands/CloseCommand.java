package library.commands;

import library.repository.FileRepository;

/**
 * Closes the currently open file.
 */
public class CloseCommand implements Command {
    private FileRepository storage;

    /**
     * @param storage the repository we use to close the file
     */
    public CloseCommand(FileRepository storage) {
        this.storage = storage;
    }

    /**
     * Closes the file and returns a message.
     * * @return result message
     */
    @Override
    public String execute(String[] args) {
        return storage.closeFile();
    }
}