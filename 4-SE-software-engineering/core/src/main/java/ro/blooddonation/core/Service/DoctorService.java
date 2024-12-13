package ro.blooddonation.core.Service;

import ro.blooddonation.core.Domain.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService
{
    /**
     * @param item
     */
    Doctor add(Doctor item);

    /**
     * @param id
     */
    void remove(Long id);

    /**
     * @param id
     */
    Optional<Doctor> update(Long id, Doctor newItem);

    /**
     * @return
     */
    List<Doctor> findAll();

    /**
     * @param id
     * @return
     */
    Optional<Doctor> findOne(Long id);
}
