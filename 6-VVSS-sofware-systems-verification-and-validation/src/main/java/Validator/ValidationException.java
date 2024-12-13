package Validator;

/**
 * Thrown if an entity instance has invalid fields
 */
public class ValidationException extends RuntimeException {
    public ValidationException(){}
    public ValidationException(String msg){
        super(msg);
    }
}
