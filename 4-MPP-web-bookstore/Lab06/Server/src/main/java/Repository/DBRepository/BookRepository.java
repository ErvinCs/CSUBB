package Repository.DBRepository;

import Domain.Book;

import java.util.List;

public interface BookRepository{
    List<Book> findAll();
    String add(String item);
    String update(String item);
    String delete(Long id);
}
