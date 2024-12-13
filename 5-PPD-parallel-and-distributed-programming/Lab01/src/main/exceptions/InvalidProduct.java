package main.exceptions;

public class InvalidProduct extends RuntimeException
{
    public InvalidProduct(String message) {
        super(message);
    }

    public InvalidProduct(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProduct(Throwable cause) {
        super(cause);
    }
}
