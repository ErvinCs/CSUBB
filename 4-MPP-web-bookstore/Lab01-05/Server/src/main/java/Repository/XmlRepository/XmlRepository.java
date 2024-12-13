package Repository.XmlRepository;

import Domain.BaseEntity;
import Util.Reader.IXmlReader;
import Util.Writer.IXmlWriter;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;
import Repository.InMemoryRepository;


import java.util.List;
import java.util.Optional;

public class XmlRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T>
{
    private String fileName;
    IXmlReader reader;
    IXmlWriter writer;

    public XmlRepository(Validator<T> validator, String fileName, IXmlReader reader, IXmlWriter writer)
    {
        super(validator);
        this.fileName = fileName;
        this.reader = reader;
        this.writer = writer;
        load();
    }

    private void load()
    {
        List<T> items = reader.loadEntities();
        for (T item : items) {
            try {
                super.save(item);
            } catch (ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<T> save(T item)
    {
        Optional<T> optional = super.save(item);
        if (optional.isPresent()) {
            return optional;
        }
        writer.save(item);
        return Optional.empty();
    }
}
