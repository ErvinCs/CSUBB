package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.Donation;
import ro.blooddonation.core.Service.DonationService;
import ro.blooddonation.web.Converter.DonationConverter;
import ro.blooddonation.web.Dto.DonationDto;
import ro.blooddonation.web.Dto.DonationDtos;
import ro.blooddonation.web.Dto.EmptyJsonResponse;

import java.util.*;

/**
 * 
 */
@RestController
public class DonationController implements IController<DonationDto, DonationDtos>
{
    private static final Logger log = LoggerFactory.getLogger(DonationController.class);

    @Autowired
    private DonationService donationService;

    @Autowired
    private DonationConverter donationConverter;


    @RequestMapping(value = "/donations", method = RequestMethod.POST)
    public DonationDto add(@RequestBody final DonationDto donationDto)
    {
        log.trace("addDonation: donationDtoMap={}", donationDto);

        Donation d = new Donation(donationDto.getDonationDate(), donationDto.getBloodQuantity());
        d.setId(donationDto.getId());
        DonationDto result = donationConverter.convertModelToDto(d);
        donationService.add(d);

        log.trace("addDonation: result={}", result);

        return result;
    }


    @RequestMapping(value = "donations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeDonation: id={}", id);

        donationService.remove(id);

        log.trace("removeDonation - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/donations/{id}", method = RequestMethod.PUT)
    public DonationDto update(@PathVariable final Long id,
                                  @RequestBody final DonationDto newDonationDto) {
        log.trace("updateDonation: id={}, donationDtoMap={}", id, newDonationDto);

        Donation d = new Donation(newDonationDto.getDonationDate(), newDonationDto.getBloodQuantity());
        d.setId(id);

        Optional<Donation> donation = donationService.update(id, d);

        Map<String, DonationDto> result = new HashMap<>();
        if (donation.isPresent())
            result.put("donation", donationConverter.convertModelToDto(donation.get()));
        else
            result.put("donation", donationConverter.convertModelToDto(new Donation()));

        log.trace("updateDonation: result={}", result);

        return result.get("donation");
    }


    @RequestMapping(value = "/donations", method = RequestMethod.GET)
    public DonationDtos getAll()
    {
        log.trace("getAllDonations");

        List<Donation> donations = donationService.findAll();

        log.trace("getAllDonations: doningCenters={}", donations);

        return new DonationDtos(donationConverter.convertModelsToDtos(donations));
    }

}