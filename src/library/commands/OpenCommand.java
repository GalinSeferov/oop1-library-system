package library.commands;

import library.repository.FileRepository;

public class OpenCommand implements Command{
    private FileRepository storage;

    public OpenCommand(FileRepository storage){
        this.storage = storage;

    }

    @Override
    public String execute(String[] args) {
        if(args.length<2) {
            return "Error! Please enter a file name";
        }
        return storage.openFile(args[1]);
    }
}
