package exceptions;

public class InvalidGrammarException extends Exception {
    public InvalidGrammarException() {
        super();
    }

    public InvalidGrammarException(String message) {
        super(message);
    }

    public InvalidGrammarException(String message, Throwable cause) {
        super(message, cause);
    }
}
