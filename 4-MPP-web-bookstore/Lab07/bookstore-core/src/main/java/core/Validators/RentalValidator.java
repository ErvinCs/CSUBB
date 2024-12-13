package core.Validators;

import core.Domain.Rental;

public class RentalValidator implements Validator<Rental>
{
    @Override
    public void validate(Rental entity) throws ValidatorException {
        String errors="";
        if(entity.getID()==null)
            errors+="Invalid ID!\n";
        if (entity.getBookID() <= 0)
            errors+="Invalid BookID!\n";
        if(entity.getClientID() <=0)
            errors+="Invalid ClientID!\n";
        if (errors.length()>0)
            throw new ValidatorException(errors);
    }

}
