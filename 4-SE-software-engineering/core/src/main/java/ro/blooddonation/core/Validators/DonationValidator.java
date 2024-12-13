package ro.blooddonation.core.Validators;

import ro.blooddonation.core.Domain.Donation;
import ro.blooddonation.core.Exceptions.ValidatorException;


public class DonationValidator extends BaseValidator<Donation> {
    @Override
    public void validate(Donation entity) throws ValidatorException {
        String errors = "";
        if (entity.getBloodQuantity() <= 0)
            errors += "Invalid blood quantity!\n";
        if (!errors.equals(""))
            throw new ValidatorException(errors);
    }
}