package web.Converter;

import core.Domain.Rental;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import web.dto.RentalDto;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDto> {

    private static final Logger log = LoggerFactory.getLogger(RentalConverter.class);

    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public RentalDto convertModelToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto(rental.getBookID(), rental.getClientID());
        rentalDto.setId(rental.getID());
        //in RentalDto - @Builder
        return rentalDto;
    }
}
