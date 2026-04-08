package library.repository;

import java.io.File;
import java.io.IOException;

public class FileRepository {
    private String currentFile;
    public String openFile(String path){
        File file = new File(path);
        try{
            if(!file.exists()){
                file.createNewFile();
                this.currentFile = path;
                return "Successfully created and opened " + file.getName();
            }
            this.currentFile = path;
            return "Successfully opened " + file.getName();
        }
        catch (IOException e) {
            return "Error! Could not open the file!";
        }
    }

    public String closeFile() {
        if (currentFile == null) {
            return "Error! No file is currently open";
        }
        File file = new File(currentFile);
        String name = file.getName();
        currentFile = null;
        return "Successfully closed " + name;
    }

    public String saveFile(){
        if(currentFile == null){
            return "Error! No file is open to save";
        }
        return "Successfully saved: "+ new File(currentFile).getName();
    }

    public String saveAs(String path){
        if(currentFile == null){
            return "Error! No file is open to save as";
        }
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            return "Successfully saved as " + file.getName();
        } catch (IOException e) {
            return "Error! Could not save to the new path!";
        }
    }
    public String getCurrentFile() {
        return currentFile;
    }
}
