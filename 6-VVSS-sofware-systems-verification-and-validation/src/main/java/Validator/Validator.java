package Validator;

/**
 * Base validator interface
 * @param <E> type of the entity to be validated
 */
public interface Validator<E> {
    public String validate(E el);
}
