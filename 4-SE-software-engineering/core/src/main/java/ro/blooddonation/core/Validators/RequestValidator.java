package ro.blooddonation.core.Validators;

import ro.blooddonation.core.Domain.Request;
import ro.blooddonation.core.Exceptions.ValidatorException;

public class RequestValidator extends BaseValidator<Request>
{
    @Override
    public void validate(Request entity) throws ValidatorException {
        String errors = "";
        if (!errors.equals(""))
            throw new ValidatorException(errors);
    }
}
