package web.Controller;

import core.Domain.Rental;
import core.Service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Converter.RentalConverter;
import web.dto.ClientDto;
import web.dto.EmptyJsonResponse;
import web.dto.RentalDto;
import web.dto.RentalsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RentalController {

    private static final Logger log = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalConverter rentalConverter;


    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public RentalsDto getRentals() {
        log.trace("getRentals");

        List<Rental> rentals = rentalService.findAll();

        log.trace("getRentals: rentals={}", rentals);

        return new RentalsDto(rentalConverter.convertModelsToDtos(rentals));
    }


    @RequestMapping(value = "/rentals/{rentalId}", method = RequestMethod.PUT)
    public RentalDto updateRental(@PathVariable final Long rentalId,
                                @RequestBody final RentalDto rentalDto) {
        log.trace("updateRental: rentalId={}, rentalDtoMap={}", rentalId, rentalDto);

        Rental rental = new Rental(rentalDto.getBid(), rentalDto.getCid());
        rental.setID(rentalId);

        Optional<Rental> rentalOptional = rentalService.updateRental(rentalId, rental);

        Map<String, RentalDto> result = new HashMap<>();
        if (rentalOptional.isPresent())
            result.put("rental", rentalConverter.convertModelToDto(rentalOptional.get()));
        else
            result.put("rental", rentalConverter.convertModelToDto(new Rental()));

        log.trace("updateRental: result={}", result);

        return result.get("rental");
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    public RentalDto createRental(@RequestBody final RentalDto rentalDto) {
        log.trace("createRental: rentalDtoMap={}", rentalDto);

        Rental r = new Rental(rentalDto.getBid(), rentalDto.getCid());
        r.setID(rentalDto.getId());
        Rental rental = rentalService.addRental(r);

        RentalDto result = rentalConverter.convertModelToDto(rental);

        log.trace("createRental: result={}", result);

        return result;
    }


    @RequestMapping(value = "rentals/{rentalId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRental(@PathVariable final Long rentalId) {
        log.trace("deleteRental: rentaltId={}", rentalId);

        rentalService.deleteRental(rentalId);

        log.trace("deleteRental - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
