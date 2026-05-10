package library.exceptions;

/**
 * Thrown when a user tries to do something they don't have permission for.
 */
public class NoPermissionException extends RuntimeException {

    /**
     * @param message error message
     */
    public NoPermissionException(String message) {
        super(message);
    }
}