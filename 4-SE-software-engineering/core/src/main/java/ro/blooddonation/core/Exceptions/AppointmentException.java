package ro.blooddonation.core.Exceptions;

public class AppointmentException extends RuntimeException
{
    public AppointmentException(String message) {
        super(message);
    }

    public AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentException(Throwable cause) {
        super(cause);
    }
}
