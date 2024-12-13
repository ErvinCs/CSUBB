package ro.blooddonation.web.Controller;

import org.springframework.http.ResponseEntity;


/**
 * 
 */
public interface IController<T, Ts>
{
    /**
     * @param item 
     * @return
     */
    T add(T item);

    /**
     * @param id 
     * @return
     */
    ResponseEntity remove(Long id);

    /**
     * @param id 
     * @param newItem 
     * @return
     */
    T update(Long id, T newItem);


    /**
     * @return
     */
    Ts getAll();

}