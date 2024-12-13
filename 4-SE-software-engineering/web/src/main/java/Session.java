import ro.blooddonation.core.Domain.*;
import ro.blooddonation.web.Converter.DCPMemberConverter;
import ro.blooddonation.web.Converter.DoctorConverter;
import ro.blooddonation.web.Converter.DonorConverter;
import ro.blooddonation.web.Dto.PersonDto;

import java.lang.reflect.Type;

public class Session {
    private PersonDto personDto;
    private Type type;
    private Boolean isAdmin;

    public Session(Person person, Type type, Boolean isAdmin)
    {
        if (type == DCPMember.class)
        {
            DCPMemberConverter dcpMemberConverter = new DCPMemberConverter();
            this.personDto = dcpMemberConverter.convertModelToDto((DCPMember)person);
        }
        else if (type == Doctor.class)
        {
            DoctorConverter doctorConverter = new DoctorConverter();
            this.personDto = doctorConverter.convertModelToDto((Doctor)person);
        }
        else if (type == Donor.class)
        {
            DonorConverter donorConverter = new DonorConverter();
            this.personDto = donorConverter.convertModelToDto((Donor)person);
        }
        else
        {
            this.personDto = null;
        }
        this.type = type;
        this.isAdmin = false;
        if (isAdmin)
        {
            this.isAdmin = isAdmin;
        }
    }

    public PersonDto getPersonDto()
    {
        return this.personDto;
    }

    public Type getType()
    {
        return this.type;
    }

    public Boolean getIsAdmin()
    {
        return this.isAdmin;
    }
}
