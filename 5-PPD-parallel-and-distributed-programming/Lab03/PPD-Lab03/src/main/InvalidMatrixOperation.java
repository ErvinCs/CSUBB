package main;

public class InvalidMatrixOperation extends RuntimeException
{
    public InvalidMatrixOperation(String message) {
        super(message);
    }

    public InvalidMatrixOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMatrixOperation(Throwable cause) {
        super(cause);
    }
}