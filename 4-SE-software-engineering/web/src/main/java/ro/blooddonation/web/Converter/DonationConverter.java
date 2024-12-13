package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.Donation;
import ro.blooddonation.web.Dto.DonationDto;

@Component
public class DonationConverter extends BaseConverter<Donation, DonationDto>
{
    private static final Logger log = LoggerFactory.getLogger(DonationConverter.class);

    @Override
    public Donation convertDtoToModel(DonationDto dto)
    {
        Donation d = new Donation(dto.getDonationDate(), dto.getBloodQuantity());
        d.setBlood(dto.getBlood());
        d.setDiseases(dto.getDiseases());
        d.setThrombocytesQuantity(dto.getThrombocytesQuantity());
        d.setRedCellsQuantity(dto.getRedCellsQuantity());
        d.setPlasmaQuantity(d.getPlasmaQuantity());
        return d;
    }

    @Override
    public DonationDto convertModelToDto(Donation donation)
    {
        DonationDto donationDto = new DonationDto(donation.getBlood(),
                                donation.getBloodQuantity(), donation.getPlasmaQuantity(),
                                donation.getThrombocytesQuantity(), donation.getRedCellsQuantity(),
                                donation.getDonationDate(), donation.getDiseases(), donation.hasDisease());
        donationDto.setId(donation.getId());
        return donationDto;
    }
}
