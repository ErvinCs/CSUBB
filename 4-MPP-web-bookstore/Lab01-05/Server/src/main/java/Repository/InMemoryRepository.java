package Repository;

import Domain.BaseEntity;
import Domain.Book;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;

import java.util.*;
import java.util.stream.Collectors;


public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>
{
    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator)
    {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if(id == null)
        {
            throw new IllegalArgumentException("Id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if(entity == null)
        {
            throw new IllegalArgumentException("Id must not be null");
        }
        try
        {
            validator.validate(entity);
        }
        catch (ValidatorException e) {
            throw new ValidatorException(e);
        }
        return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if(id == null)
        {
            throw new IllegalArgumentException("Id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null)
        {
            throw new IllegalArgumentException("Entity must not be null");
        }
        try
        {
            validator.validate(entity);
        }
        catch (ValidatorException e){
            throw new ValidatorException(e);
        }

        return Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k,v) -> entity));
    }
}
