package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Hospital;
import ro.blooddonation.core.Repo.HospitalRepo;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService
{
    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

    @Autowired
    private HospitalRepo hospitalRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Hospital add(Hospital item)
    {
        log.trace("addHospital: hospital={}", item);

        hospitalRepo.save(item);

        log.trace("addHospital --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeHospital: id={}", id);

        hospitalRepo.deleteById(id);

        log.trace("removeHospital --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Hospital> update(Long id, Hospital newItem)
    {
        log.trace("updateHospital: hospital={}", newItem);

        Optional<Hospital> hospitalOptional = hospitalRepo.findById(id);

        hospitalOptional.ifPresent(h ->
                {
                    h.setAddress(newItem.getAddress());
                    h.setDoctors(newItem.getDoctors());
                }
        );

        log.trace("updateHospital: hospitalOptional={}", hospitalOptional);

        return hospitalOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Hospital> findAll()
    {
        log.trace("findAllHospitals");

        List<Hospital> hospitals = hospitalRepo.findAll();

        log.trace("findAll: hospitals={}", hospitals);

        return hospitals;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Hospital> findOne(Long id)
    {
        log.trace("findHospital: HospitalId={}", id);

        Optional<Hospital> hospital = hospitalRepo.findById(id);

        log.trace("findHospital: Hospital={}", hospital);

        return hospital;
    }
}
