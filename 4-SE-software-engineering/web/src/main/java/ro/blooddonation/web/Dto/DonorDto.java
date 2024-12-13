package ro.blooddonation.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.blooddonation.core.Domain.Account;
import ro.blooddonation.core.Domain.Donation;

import javax.persistence.ElementCollection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonorDto extends PersonDto {

    private DonationDto currDonation;
    private LocalDate currAppointment;
    private LocalDate lastDonation;
    private List<DonationDto> donationHistory;
}
