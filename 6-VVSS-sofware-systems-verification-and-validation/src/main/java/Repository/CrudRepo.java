package Repository;

/**
 * Base container interface
 * @param <E> type of the entity to pe persisted
 * @param <ID> type of the id of the entity to be persisted
 */
public interface CrudRepo <E,ID> {
    public E save(E elem);
    public E update(E elem);
    public E delete(ID id);
    public E findOne(ID id);
    public Iterable<E> findAll();
    public int size();

}
