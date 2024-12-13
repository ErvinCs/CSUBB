package ro.blooddonation.web.Dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HospitalsDto
{
    public Set<HospitalDto> hospitals;

}
