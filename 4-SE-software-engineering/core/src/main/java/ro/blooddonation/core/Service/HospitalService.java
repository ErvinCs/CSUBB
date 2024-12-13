package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService
{
    /**
     * @param item
     */
    Hospital add(Hospital item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Hospital> update(Long id, Hospital newItem);

    /**
     * @return
     */
    List<Hospital> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Hospital> findOne(Long id);
}
