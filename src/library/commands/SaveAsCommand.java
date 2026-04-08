package library.commands;

import library.repository.FileRepository;

public class SaveAsCommand implements Command {
    private FileRepository storage;

    public SaveAsCommand(FileRepository storage) {
        this.storage = storage;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2) {
            return "Error! Please specify a new file path.";
        }
        return storage.saveAs(args[1]);
    }
}