package Service;

import Domain.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.List;

public class BookServiceClient implements BookService
{
    @Autowired
    private BookService bService;

    @Override
    public List<Book> findAll() throws RemoteException {
        return bService.findAll();
    }

    @Override
    public String add(String item) throws RemoteException {
        return bService.add(item);
    }

    @Override
    public String update(String item) throws RemoteException {
        return bService.update(item);
    }

    @Override
    public String delete(Long id) throws RemoteException {
        return bService.delete(id);
    }
}
