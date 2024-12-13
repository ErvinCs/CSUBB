package ro.blooddonation.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.blooddonation.core.Domain.Blood;
import ro.blooddonation.core.Domain.BloodEnum;
import ro.blooddonation.core.Domain.Doctor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestDto extends BaseDto
{
    private BloodEnum blood;
    private Integer urgency;
    private DoctorDto doctor;
    private String status;

    @Override
    public String toString()
    {
        return "Request{blood=" + this.doctor.toString() +
                ", urgency=" + this.urgency.toString() +
                ", doctor=" + this.doctor.toString() +
                ", status=" + this.status.toString() +
                "}" + super.toString();
    }
}
