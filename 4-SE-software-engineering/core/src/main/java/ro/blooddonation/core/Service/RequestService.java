package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService
{
    /**
     * @param item
     */
    Request add(Request item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Request> update(Long id, Request newItem);

    /**
     * @return
     */
    List<Request> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Request> findOne(Long id);
}
