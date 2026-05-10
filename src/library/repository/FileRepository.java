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

/**
 * Stores all data - books, users and the current file.
 */
public class FileRepository {
    private String currentFile;
    private String loggedUser;
    private AccessLevel loggedUserRole;
    private Map<String, User> registeredUsers = new HashMap<>();
    private List<Book> books = new ArrayList<>();

    /**
     * Default admin account is created on startup.
     */
    public FileRepository() {
        registeredUsers.put("admin", new User("admin", "i<3Java", AccessLevel.ADMIN));
    }

    /**
     * Opens a file, creates it if it doesn't exist.
     * @param path file path
     * @return result message
     */
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

    /**
     * Closes the current file.
     * @return result message
     */
    public String closeFile() {
        if (currentFile == null) {
            return "Error! No file is currently open";
        }
        File file = new File(currentFile);
        String name = file.getName();
        currentFile = null;
        return "Successfully closed " + name;
    }

    /**
     * Saves the current file.
     * @return result message
     */
    public String saveFile() {
        if (currentFile == null) {
            return "Error! No file is open to save";
        }
        return "Successfully saved: " + new File(currentFile).getName();
    }

    /**
     * Saves the file under a new name.
     * @param path new file path
     * @return result message
     */
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

    /**
     * @return current open file path
     */
    public String getCurrentFile() {
        return currentFile;
    }

    /**
     * Checks if the credentials are correct.
     * @return true if correct
     */
    public boolean authenticate(String username, String password) {
        User user = registeredUsers.get(username);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    /**
     * Logs in the user.
     * @param username the username
     */
    public void login(String username) {
        User user = registeredUsers.get(username);
        this.loggedUser = username;
        this.loggedUserRole = user.getAccessLevel();
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        this.loggedUser = null;
        this.loggedUserRole = null;
    }

    /**
     * @return logged in username
     */
    public String getLoggedUser() {
        return loggedUser;
    }

    /**
     * @return role of the logged in user
     */
    public AccessLevel getLoggedUserRole() {
        return loggedUserRole;
    }

    /**
     * Adds a book to the library.
     * @param book the book to add
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * @return all books in the library
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Adds a new user.
     * @param username the username
     * @param password the password
     * @param role the role
     */
    public void addUser(String username, String password, AccessLevel role) {
        registeredUsers.put(username, new User(username, password, role));
    }

    /**
     * Removes a user. Admin cannot be removed.
     * @param username the username to remove
     * @return true if removed
     */
    public boolean removeUser(String username) {
        if (username.equals("admin")) return false;
        return registeredUsers.remove(username) != null;
    }

    /**
     * @param username the username to check
     * @return true if the user exists
     */
    public boolean userExists(String username) {
        return registeredUsers.containsKey(username);
    }
}