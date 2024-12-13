package ro.blooddonation.core.Validators;

import ro.blooddonation.core.Domain.BaseEntity;
import ro.blooddonation.core.Exceptions.ValidatorException;

public class BaseValidator<T extends BaseEntity> implements Validator<T>
{
    public void validate(T entity) throws ValidatorException
    {
        if(entity.getId() == null)
            throw new ValidatorException("Id is null!");
    }
}
