package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Donor;
import ro.blooddonation.core.Repo.DonorRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService
{
    private static final Logger log = LoggerFactory.getLogger(DonorService.class);

    @Autowired
    private DonorRepo donorRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Donor add(Donor item)
    {
        log.trace("addDonor: donor={}", item);

        donorRepo.save(item);

        log.trace("addDonor --- method finished");

        return item;
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public void remove(Long id)
    {
        log.trace("removeDonor: id={}", id);

        donorRepo.deleteById(id);

        log.trace("removeDonor --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Donor> update(Long id, Donor newItem)
    {
        log.trace("updateDonor: donor={}", newItem);

        Optional<Donor> donorOptional = donorRepo.findById(id);

        donorOptional.ifPresent(d ->
                {
                    d.setCurrAppointment(newItem.getCurrAppointment());
                    d.setCurrDonation(newItem.getCurrDonation());
                    d.setLastDonation(newItem.getLastDonation());
                }
        );

        log.trace("updateDonor: donorOptional={}", donorOptional);

        return donorOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Donor> findAll()
    {
        log.trace("findAllDonors");

        List<Donor> donors = donorRepo.findAll();

        log.trace("findAll: Donors={}", donors);

        return donors;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Donor> findOne(Long id)
    {
        log.trace("finDonor: DonorId={}", id);

        Optional<Donor> donor = donorRepo.findById(id);

        log.trace("findDonor: Donor={}", donor);

        return donor;
    }
}
