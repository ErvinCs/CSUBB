package exceptions;

/**
 * Signals an error at lexical level in the input program.
 */
public class LexicalError extends Exception {
    public LexicalError() {
        super();
    }

    public LexicalError(String message) {
        super(message);
    }

    public LexicalError(String message, Throwable cause) {
        super(message, cause);
    }
}
