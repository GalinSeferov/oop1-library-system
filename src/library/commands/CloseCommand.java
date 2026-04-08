package library.commands;

import library.repository.FileRepository;

public class CloseCommand implements Command{
    private FileRepository storage;
    public CloseCommand(FileRepository storage){
        this.storage = storage;
    }


    @Override
    public String execute(String[] args) {
        return storage.closeFile();
    }
}
