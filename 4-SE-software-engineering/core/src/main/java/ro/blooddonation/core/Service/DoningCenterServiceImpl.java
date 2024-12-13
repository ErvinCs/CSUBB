package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.DoningCenter;
import ro.blooddonation.core.Repo.DoningCenterRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DoningCenterServiceImpl implements DoningCenterService
{
    private static final Logger log = LoggerFactory.getLogger(HospitalService.class);

    @Autowired
    private DoningCenterRepo dcRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public DoningCenter add(DoningCenter item)
    {
        log.trace("addDoningCenter: doningCenter={}", item);

        dcRepo.save(item);

        log.trace("addDoningCenter --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeDoningCenter: id={}", id);

        dcRepo.deleteById(id);

        log.trace("removeDoningCenter --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<DoningCenter> update(Long id, DoningCenter newItem)
    {
        log.trace("updateDoningCenter: doningCenter={}", newItem);

        Optional<DoningCenter> dcOptional = dcRepo.findById(id);

        dcOptional.ifPresent(dc ->
                {
                    dc.setAddress(newItem.getAddress());
                }
        );

        log.trace("updateDoningCenter: doningCenterOptional={}", dcOptional);

        return dcOptional;
    }

    /**
     * @return
     */
    @Override
    public List<DoningCenter> findAll()
    {
        log.trace("findAllDoningCenters");

        List<DoningCenter> doningCenters = dcRepo.findAll();

        log.trace("findAll: doningCenters={}", doningCenters);

        return doningCenters;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<DoningCenter> findOne(Long id)
    {
        log.trace("finDoningCenter: DoningCenterId={}", id);

        Optional<DoningCenter> dc = dcRepo.findById(id);

        log.trace("findDoningCenter: Hospital={}", dc);

        return dc;
    }
}
