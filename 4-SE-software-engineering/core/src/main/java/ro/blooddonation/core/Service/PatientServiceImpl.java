package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Patient;
import ro.blooddonation.core.Repo.PatientRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService
{
    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepo patientRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Patient add(Patient item)
    {
        log.trace("addPatient: donation={}", item);

        patientRepo.save(item);

        log.trace("addPatient --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeDonation: id={}", id);

        patientRepo.deleteById(id);

        log.trace("removePatient --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Patient> update(Long id, Patient newItem)
    {
        log.trace("updatePatient: patient={}", newItem);

        Optional<Patient> patientOptional = patientRepo.findById(id);

        patientOptional.ifPresent(p ->
                {
                    p.setUrgency(newItem.getUrgency());
                    p.setRequestedBloodQuantity(newItem.getRequestedBloodQuantity());
                    p.setBlood(newItem.getBlood());
                    p.setDoctor(newItem.getDoctor());

                    p.setFirstName(newItem.getFirstName());
                    p.setLastName(newItem.getLastName());
                    p.setResidence(newItem.getResidence());
                    p.setAddress(newItem.getAddress());
                }
        );

        log.trace("updatePatient: patientOptional={}", patientOptional);

        return patientOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Patient> findAll()
    {
        log.trace("findAllPatients");

        List<Patient> patients = patientRepo.findAll();

        log.trace("findAll: Patients={}", patients);

        return patients;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Patient> findOne(Long id)
    {
        log.trace("findPatient: PatientId={}", id);

        Optional<Patient> patient = patientRepo.findById(id);

        log.trace("findPatient: Patient={}", patient);

        return patient;
    }
}
