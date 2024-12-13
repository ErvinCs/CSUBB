package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService
{
    /**
     * @param item
     */
    Patient add(Patient item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Patient> update(Long id, Patient newItem);

    /**
     * @return
     */
    List<Patient> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Patient> findOne(Long id);
}
