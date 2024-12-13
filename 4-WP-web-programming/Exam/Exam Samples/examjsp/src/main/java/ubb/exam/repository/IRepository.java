package ubb.exam.repository;

import ubb.exam.model.BaseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IRepository <T extends BaseEntity> {
    void add(T entity) throws IOException;
    void delete(Long id);
    void update(T entity) throws IOException;
    Optional<T> getById(Long id) throws IOException;
    List<T> getAll() throws IOException;

}
