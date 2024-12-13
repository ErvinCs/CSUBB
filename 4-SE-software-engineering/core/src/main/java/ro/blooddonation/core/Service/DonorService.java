package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Donor;

import java.util.List;
import java.util.Optional;

public interface DonorService
{
    /**
     * @param item
     */
    Donor add(Donor item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Donor> update(Long id, Donor newItem);

    /**
     * @return
     */
    List<Donor> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Donor> findOne(Long id);
}
