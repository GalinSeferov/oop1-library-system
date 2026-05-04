package library.repository;

import library.models.Book;
import library.models.User;
import library.models.AccessLevel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRepository {
    private String currentFile;
    private String loggedUser;
    private AccessLevel loggedUserRole;
    private Map<String, User> registeredUsers = new HashMap<>();
    private List<Book> books = new ArrayList<>();

    public FileRepository() {
        registeredUsers.put("admin", new User("admin", "i<3Java", AccessLevel.ADMIN));
    }

    public String openFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
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

    public String saveFile() {
        if (currentFile == null) {
            return "Error! No file is open to save";
        }
        return "Successfully saved: " + new File(currentFile).getName();
    }

    public String saveAs(String path) {
        if (currentFile == null) {
            return "Error! No file is open to save as";
        }
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            this.currentFile = path;
            return "Successfully saved as " + file.getName();
        } catch (IOException e) {
            return "Error! Could not save to the new path!";
        }
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public boolean authenticate(String username, String password) {
        User user = registeredUsers.get(username);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    public void login(String username) {
        User user = registeredUsers.get(username);
        this.loggedUser = username;
        this.loggedUserRole = user.getAccessLevel();
    }

    public void logout() {
        this.loggedUser = null;
        this.loggedUserRole = null;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public AccessLevel getLoggedUserRole() {
        return loggedUserRole;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addUser(String username, String password, AccessLevel role) {
        registeredUsers.put(username, new User(username, password, role));
    }

    public boolean removeUser(String username) {
        if (username.equals("admin")) return false;
        return registeredUsers.remove(username) != null;
    }

    public boolean userExists(String username) {
        return registeredUsers.containsKey(username);
    }
}