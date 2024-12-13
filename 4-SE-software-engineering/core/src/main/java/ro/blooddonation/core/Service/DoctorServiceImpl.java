package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Repo.DoctorRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService
{
    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepo doctorRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Doctor add(Doctor item)
    {
        log.trace("addDoctor: doctor={}", item);

        doctorRepo.save(item);

        log.trace("addDoctor --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeDoctor: id={}", id);

        doctorRepo.deleteById(id);

        log.trace("removeDoctor --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Doctor> update(Long id, Doctor newItem)
    {
        log.trace("updateDoctor: doctor={}", newItem);

        Optional<Doctor> doctorOptional = doctorRepo.findById(id);

        doctorOptional.ifPresent(d ->
                {
                    d.setHospital(newItem.getHospital());
                    d.setPatients(newItem.getPatients());

                    d.setFirstName(newItem.getFirstName());
                    d.setLastName(newItem.getLastName());
                    d.setResidence(newItem.getResidence());
                    d.setAddress(newItem.getAddress());
                }
        );

        log.trace("updateDoctor: doctorOptional={}", doctorOptional);

        return doctorOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Doctor> findAll()
    {
        log.trace("findAllDoctors");

        List<Doctor> doctors = doctorRepo.findAll();

        log.trace("findAll: Doctors={}", doctors);

        return doctors;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Doctor> findOne(Long id)
    {
        log.trace("findDoctor: DoctorId={}", id);

        Optional<Doctor> doctor = doctorRepo.findById(id);

        log.trace("findDoctor: Doctor={}", doctor);

        return doctor;
    }
}
