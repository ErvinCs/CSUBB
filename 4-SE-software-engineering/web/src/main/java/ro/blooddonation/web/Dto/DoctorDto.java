package ro.blooddonation.web.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto extends PersonDto
{
    private HospitalDto hospital;
    private PatientsDto patients;


    @Override
    public String toString()
    {
        return "DoctorDto{hospital=" + this.hospital.toString() +
                ", patientsNo=" + //this.patients.getPatients().size() +
                "}" + super.toString();
    }

}
