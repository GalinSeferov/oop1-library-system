package library.exceptions;

/**
 * Thrown when the user types an invalid command.
 */
public class InvalidCommandException extends RuntimeException {

    /**
     * @param message error message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}