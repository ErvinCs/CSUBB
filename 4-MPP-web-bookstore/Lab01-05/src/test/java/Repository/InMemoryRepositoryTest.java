package Repository;

import Domain.Book;
import Domain.Validators.BookValidator;
import Domain.Validators.BookstoreException;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class InMemoryRepositoryTest {

    private BookValidator validator=new BookValidator();
    private InMemoryRepository<Long, Book> repo=new InMemoryRepository<>(validator);
    private Book b1=new Book("Book1","Author1",1);
    private Book b2=new Book("Book2","Author2",2);
    @Test
    public void testFindOne() throws BookstoreException {
        b1.setID((long) 1);
        b2.setID((long) 2);
        repo.save(b1);
        repo.save(b2);
        assertEquals("ID not found!", Optional.of(b1),repo.findOne((long) 1));
    }

    @Test
    public void testFindAll() throws BookstoreException {
        b1.setID((long) 1);
        b2.setID((long) 2);
        repo.save(b1);
        repo.save(b2);
        Set<Book> books=new HashSet<>();
        books.add(b1);
        books.add(b2);
        assertEquals("Repo lists should be equal!",books,repo.findAll());

    }

    @Test
    public void testSave() throws BookstoreException {
        b1.setID(1L);
        assertEquals("Save not configured properly!",Optional.empty(),repo.save(b1));

    }

    @Test(expected = ValidatorException.class)
    public void testSaveException() throws BookstoreException {
        b1.setID(null);
        repo.save(b1);
    }

    @Test
    public void testDelete() throws BookstoreException {
        b1.setID(1L);
        repo.save(b1);
        assertEquals("Delete not configured properly!",Optional.of(b1),repo.delete(1L));

    }

    @Test
    public void testUpdate() throws BookstoreException {
        b1.setID(1L);
        repo.save(b1);
        String NEW_NAME="newName";
        b1.setName(NEW_NAME);
        assertEquals("Update not configured properly!",Optional.of(b1),repo.update(b1));
    }

    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws BookstoreException {
        b1.setID(1L);
        repo.save(b1);
        b1.setID(null);
        repo.update(b1);
    }

}
