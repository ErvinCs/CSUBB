package ro.blooddonation.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.blooddonation.core.Domain.BloodEnum;
import ro.blooddonation.core.Domain.DiseasesEnum;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonationDto extends BaseDto
{
    private BloodEnum blood;
    private Double bloodQuantity;
    private Double plasmaQuantity;
    private Double thrombocytesQuantity;
    private Double redCellsQuantity;
    private LocalDate donationDate;
    private Map<DiseasesEnum, Boolean> diseases;
    private Boolean hasDisease;
}
