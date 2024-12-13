package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService
{
    /**
     * @param item
     */
    Donation add(Donation item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Donation> update(Long id, Donation newItem);

    /**
     * @return
     */
    List<Donation> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Donation> findOne(Long id);
}
