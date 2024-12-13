package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.Donation;
import ro.blooddonation.core.Domain.DoningCenter;
import ro.blooddonation.core.Domain.Donor;
import ro.blooddonation.core.Service.DonorService;
import ro.blooddonation.web.Converter.DonorConverter;
import ro.blooddonation.web.Dto.DonorDto;
import ro.blooddonation.web.Dto.DonorDtos;
import ro.blooddonation.web.Dto.EmptyJsonResponse;

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
@RestController
public class DonorController implements IController<DonorDto, DonorDtos>
{

    private static final Logger log = LoggerFactory.getLogger(DonorController.class);

    @Autowired
    private DonorService donorService;

    @Autowired
    private DonorConverter donorConverter;


    @RequestMapping(value = "/donors", method = RequestMethod.POST)
    public DonorDto add(@RequestBody final DonorDto donorDto)
    {
        log.trace("addDonor: donorDtoMap={}", donorDto);

        Donor donor = new Donor(donorDto.getFirstName(), donorDto.getLastName(), donorDto.getBDay(),
                donorDto.getAddress(), donorDto.getResidence(), donorDto.getCNP(), null);
        donor.setId(donorDto.getId());
        donorService.add(donor);

        DonorDto result = donorConverter.convertModelToDto(donor);

        log.trace("addDonor: result={}", result);

        return result;
    }


    @RequestMapping(value = "donors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeDonor: id={}", id);

        donorService.remove(id);

        log.trace("removeDonor - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/donors/{id}", method = RequestMethod.PUT)
    public DonorDto update(@PathVariable final Long id,
                            @RequestBody final DonorDto newDonorDto) {
        log.trace("updateDonor: id={}, donorDtoMap={}", id, newDonorDto);

        Donor d = new Donor(newDonorDto.getFirstName(), newDonorDto.getLastName(), newDonorDto.getBDay(),
                newDonorDto.getAddress(), newDonorDto.getResidence(), newDonorDto.getCNP(), null);
        d.setId(id);

        Optional<Donor> donor = donorService.update(id, d);

        Map<String, DonorDto> result = new HashMap<>();
        if (donor.isPresent())
            result.put("donor", donorConverter.convertModelToDto(donor.get()));
        else
            result.put("donor", donorConverter.convertModelToDto(new Donor()));

        log.trace("updateDonor: result={}", result);

        return result.get("donor");
    }


    @RequestMapping(value = "/donors", method = RequestMethod.GET)
    public DonorDtos getAll()
    {
        log.trace("getAllDonors");

        List<Donor> donors = donorService.findAll();

        log.trace("getAllDonors: doctors={}", donors);

        return new DonorDtos(donorConverter.convertModelsToDtos(donors));
    }
}