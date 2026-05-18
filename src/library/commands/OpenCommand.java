package library.commands;

import library.repository.FileRepository;

/**
 * Command to open a library file.
 */
public class OpenCommand implements Command{
    private FileRepository storage;

    /**
     * @param storage the repository used to open the file
     */

    public OpenCommand(FileRepository storage){
        this.storage = storage;

    }

    /**
     * Opens the file and returns a message.
     * @param args the file name to open
     * @return result message
     */

    @Override
    public String execute(String[] args) {
        if(args.length<2) {
            return "Error! Please enter a file name";
        }
        return storage.openFile(args[1]);
    }
}
