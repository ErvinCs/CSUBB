package Service;

import Domain.Book;
import Repository.DBRepository.BookRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.rmi.RemoteException;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> findAll() throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        BookRepository bookRepository = context.getBean(BookRepository.class);

        return bookRepository.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        BookRepository bookRepository = context.getBean(BookRepository.class);

        return bookRepository.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        BookRepository bookRepository = context.getBean(BookRepository.class);

        return bookRepository.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("Config");
        BookRepository bookRepository = context.getBean(BookRepository.class);

        return bookRepository.delete(id);
    }
}
