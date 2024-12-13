package ro.blooddonation.core.Validators;

import ro.blooddonation.core.Domain.BaseEntity;
import ro.blooddonation.core.Exceptions.ValidatorException;

public interface Validator<T extends BaseEntity> {
    void validate(T entity) throws ValidatorException;
}