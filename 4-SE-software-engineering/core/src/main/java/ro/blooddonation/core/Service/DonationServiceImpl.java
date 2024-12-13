package ro.blooddonation.core.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blooddonation.core.Domain.Donation;
import ro.blooddonation.core.Repo.DonationRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService
{
    private static final Logger log = LoggerFactory.getLogger(DonationService.class);

    @Autowired
    private DonationRepo donationRepo;

    /**
     *
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Donation add(Donation item)
    {
        log.trace("addDonation: donation={}", item);

        donationRepo.save(item);

        log.trace("addDonation --- method finished");

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

        donationRepo.deleteById(id);

        log.trace("removeDonation --- method finished");
    }

    /**
     * @param id
     */
    @Override
    @Transactional
    public Optional<Donation> update(Long id, Donation newItem)
    {
        log.trace("updateDonation: donation={}", newItem);

        Optional<Donation> donationOptional = donationRepo.findById(id);

        donationOptional.ifPresent(d ->
                {
                    d.setBlood(newItem.getBlood());
                    d.setPlasmaQuantity(newItem.getPlasmaQuantity());
                    d.setRedCellsQuantity(newItem.getRedCellsQuantity());
                    d.setThrombocytesQuantity(newItem.getThrombocytesQuantity());
                    d.setDiseases(newItem.getDiseases());
                }
        );

        log.trace("updateDonation: donationOptional={}", donationOptional);

        return donationOptional;
    }

    /**
     * @return
     */
    @Override
    public List<Donation> findAll()
    {
        log.trace("findAllDonations");

        List<Donation> donations = donationRepo.findAll();

        log.trace("findAll: Donations={}", donations);

        return donations;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Donation> findOne(Long id)
    {
        log.trace("finDonation: DonationId={}", id);

        Optional<Donation> donation = donationRepo.findById(id);

        log.trace("findDoningCenter: Donation={}", donation);

        return donation;
    }
}
