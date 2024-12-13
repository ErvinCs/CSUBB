package ro.blooddonation.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.blooddonation.core.Domain.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto extends PersonDto {

    private DoctorDto doctor;
    private BloodEnum blood;
    private Double requestedBloodQuantity;
    private Integer urgency;

    @Override
    public String toString()
    {
        return "Patient{doctor=" + this.doctor.toString() +
                ", blood=" + this.blood.toString() +
                ", requestedQuantity=" + this.requestedBloodQuantity.toString() +
                ", urgency=" + this.urgency.toString() +
                "}" + super.toString();
    }

}
