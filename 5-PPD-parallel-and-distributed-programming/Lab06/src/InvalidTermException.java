public class InvalidTermException extends Exception {
    public InvalidTermException() {
        super();
    }

    public InvalidTermException(String message) {
        super(message);
    }

    public InvalidTermException(String message, Throwable cause) {
        super(message, cause);
    }
}
