package Domain;

/**
 * Base entity interface.
 * @param <ID> unique identifier for each entity instance
 */
public interface hasID<ID> {
    ID getID();
    void setID(ID id);
}