package Repository.FileRepository;

import Domain.BaseEntity;
import Domain.Validators.Validator;
import Repository.Repository;
import Repository.InMemoryRepository;

import java.util.Optional;

public abstract class FileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T>
    {
        String fileName;

    FileRepository(Validator<T> validator, String fileName)
    {
        super(validator);
        this.fileName = fileName;
        load();
    }

    abstract void load();

    abstract void saveToFile(T item);

    @Override
    public Optional<T> save(T item)
    {
        Optional<T> optional = super.save(item);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(item);
        return Optional.empty();
    }


}
