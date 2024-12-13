package ro.blooddonation.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonDto extends BaseDto
{
    protected Long CNP;

    protected String firstName;

    protected String lastName;

    protected LocalDate bDay;

    protected String address;

    protected String residence;

    public String toString()
    {
        return "Person{firstName=" + this.firstName.toString() +
                ", lastName=" + this.lastName.toString() +
                ", bDay=" + this.bDay.toString() +
                ", address=" + this.address.toString() +
                ", residence=" + this.residence.toString() +
                ", CNP=" + this.CNP.toString() +
                "}" + super.toString();
    }

}
