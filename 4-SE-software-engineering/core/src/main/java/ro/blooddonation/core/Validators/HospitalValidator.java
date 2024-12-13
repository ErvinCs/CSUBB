package ro.blooddonation.core.Validators;

import ro.blooddonation.core.Domain.Hospital;
import ro.blooddonation.core.Exceptions.ValidatorException;

public class HospitalValidator extends BaseValidator<Hospital> {
    @Override
    public void validate(Hospital entity) throws ValidatorException {
        String errors="";
        if (!errors.equals(""))
            throw new ValidatorException(errors);
    }
}
