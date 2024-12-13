package Service;

import Domain.Book;
import Domain.Validators.BookValidator;
import Domain.Validators.ValidatorException;
import Repository.InMemoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class BookServiceTest {

    private BookValidator validator=new BookValidator();
    private InMemoryRepository<Long, Book> repo=new InMemoryRepository<>(validator);
    private BookService bookservice = new BookService(repo);
    private Book b1=new Book("Book1","Author1",1);
    private Book b2=new Book("Book2","Author2",2);

    @Test
    public void testAddBook() throws ValidatorException
    {
        b1.setID((long)1);
        b2.setID((long)2);

        bookservice.add(b1);
        bookservice.add(b2);

        Set<Book> books=new HashSet<>();
        books.add(b1);
        books.add(b2);
        assertEquals("Repo lists should be equal.",books, repo.findAll());

    }

    @Test
    public void testGetAllBooks()
    {
        b1.setID((long)1);
        b2.setID((long)2);

        bookservice.add(b1);
        bookservice.add(b2);

        assertEquals("Repo lists should be equal.", bookservice.getAll(), repo.findAll());

    }

    @Test
    public void testFilterBooksByName()
    {
        String name = "somebook";
        b1.setID((long)1);
        b2.setID((long)2);

        repo.save(b1);
        repo.save(b2);

        Set<Book> books=new HashSet<>();

        assertEquals("Set should be emtpy.", books, bookservice.filter(name));

    }

    @Test
    public void testDelete()
    {
        Book b1 = new Book("b1","a1",1233);
        b1.setID(1L);
        Book b2 = new Book("b2","a2",3233);
        b2.setID(2L);

        repo.save(b1);
        repo.save(b2);

        Set<Book> books=new HashSet<>();
        books.add(b1);
        bookservice.delete(2L);
        assertEquals("Sets should be equal",books,bookservice.getAll());
    }
    @Test
    public void testUpdate()
    {
        Book b1 = new Book("b1","a1",1233);
        b1.setID(1L);
        repo.save(b1);

        Set<Book> books=new HashSet<>();
        Book b2 = new Book("updatedName","updatedAuthor",1233);
        b2.setID(1L);
        books.add(b2);
        bookservice.update(b2);
        assertEquals("Sets should be equal",books,bookservice.getAll());
    }
}
