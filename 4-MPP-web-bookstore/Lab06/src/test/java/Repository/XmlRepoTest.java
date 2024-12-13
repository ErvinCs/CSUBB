package Repository;

import Domain.Book;
import Domain.Validators.BookValidator;
import Domain.Validators.Validator;
import Repository.XmlRepository.XmlRepository;
import Util.Reader.IXmlReader;
import Util.Reader.XmlBookReader;
import Util.Writer.IXmlWriter;
import Util.Writer.XmlBookWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Optional;

public class XmlRepoTest {

    private Validator<Book> bookValidator = new BookValidator();
    private IXmlReader<Book> bookReader = new XmlBookReader("./data/testbooks.xml");
    private IXmlWriter<Book> bookWriter = new XmlBookWriter("./data/testbooks.xml");
    private Repository<Long, Book> repo = new XmlRepository<>(bookValidator, "./data/testbooks.xml", bookReader, bookWriter);


    @Test
    public void testSave()
    {
        Book book = new Book("book", "author", 1234);
        Long id = 1L;
        book.setID(id);

        assertNotEquals(Optional.empty(), repo.save(book));
    }
}
