package Repository;

import Domain.hasID;
import Validator.ValidationException;
import Validator.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements CrudRepo methods
 * @param <E> type of the entity to pe persisted
 * @param <ID> type of the id of the entity to be persisted
 */
public abstract class AbstractRepo <E extends hasID<ID>,ID> implements CrudRepo<E,ID> {
    private Validator<E> validator;
    private Map<ID,E> repo;
    public AbstractRepo(Validator<E> v){
        validator=v;
        repo=new HashMap<>();
    }

    /***
     * Salveaza element
     * @param el
     * @return elementul salvat
     */
    public E save(E el){
        String msg=validator.validate(el);
        if(msg.equals("")){
            repo.putIfAbsent(el.getID(),el);
            return findOne(el.getID());
        }
        else throw new ValidationException(msg);
    }

    /***
     * Cauta element dupa id
     * @param id
     * @return elementul gasit
     */
    public E findOne(ID id){
        return repo.get(id);
    }

    /***
     * Modifica element
     * @param elem
     * @return elementul gasit
     */
    public E update(E elem){
        String msg=validator.validate(elem);
        if(msg.equals("")){
            E el2=findOne(elem.getID());
            repo.put(elem.getID(),elem);
            return el2;

        }
        else throw new ValidationException(msg);
    }

    /***
     * Sterge element dupa id-ul dat
     * @param id
     * @return elementul sters
     */
    public E delete(ID id){
        E el2=findOne(id);
        if(el2!=null)
            repo.remove(id);
        return el2;
    }

    /***
     * @return numarul de elemente
     */
    public int size(){
        return repo.size();
    }

    /***
     * @return toate elementele
     */
    @Override
    public Iterable<E> findAll() {
        return repo.values();
    }
}

