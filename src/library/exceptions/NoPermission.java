package library.exceptions;

public class NoPermission extends RuntimeException {
    public NoPermission(String message) {
        super(message);
    }
}
