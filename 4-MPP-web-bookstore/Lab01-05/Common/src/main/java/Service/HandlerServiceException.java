package Service;

public class HandlerServiceException extends RuntimeException {

    public HandlerServiceException(String message) {super(message);}

    public HandlerServiceException(String message, Throwable cause) {super(message, cause);}

    public HandlerServiceException(Throwable cause) {super(cause);}
}
