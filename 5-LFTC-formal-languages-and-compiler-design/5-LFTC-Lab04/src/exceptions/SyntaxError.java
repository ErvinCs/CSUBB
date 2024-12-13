package exceptions;

/**
 * Signals an error at syntactical level in the input program.
 */
public class SyntaxError extends Exception {
    public SyntaxError() {
        super();
    }

    public SyntaxError(String message) {
        super(message);
    }

    public SyntaxError(String message, Throwable cause) {
        super(message, cause);
    }
}
