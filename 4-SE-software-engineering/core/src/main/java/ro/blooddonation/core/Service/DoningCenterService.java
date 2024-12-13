package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.DoningCenter;

import java.util.List;
import java.util.Optional;

public interface DoningCenterService
{
    /**
     * @param item
     */
    DoningCenter add(DoningCenter item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<DoningCenter> update(Long id, DoningCenter newItem);

    /**
     * @return
     */
    List<DoningCenter> findAll();

    /**
     * @param id
     * @return
     */
    Optional<DoningCenter> findOne(Long id);
}
