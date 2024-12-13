package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BookTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final int ISBN = 1;
    private static final int NEW_ISBN=2;
    private static final String NAME = "Book1";
    private static final String NEW_NAME="Book2";
    private static final String AUTHOR = "Author1";
    private static final String NEW_AUTHOR="Author2";

    private Book b;

    @Before
    public void setUp() throws Exception {
        b = new Book(NAME, AUTHOR, ISBN);
        b.setID(ID);
    }

    @After
    public void tearDown() throws Exception {
        b=null;
    }

    @Test
    public void testGetISBN() throws Exception {
        assertEquals("ISBNs should be equal", ISBN, b.getISBN());
    }

    @Test
    public void testSetISBN() throws Exception {
        b.setISBN(NEW_ISBN);
        assertEquals("ISBNs should be equal", NEW_ISBN, b.getISBN());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, b.getID());
    }

    @Test
    public void testSetId() throws Exception {
        b.setID(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, b.getID());
    }


    @Test
    public void testGetName() throws Exception {
        assertEquals("Names should be equal",NAME,b.getName());
    }

    @Test
    public void testSetName() throws Exception {
        b.setName(NEW_NAME);
        assertEquals("Names should be equal",NEW_NAME,b.getName());
    }

    @Test
    public void testGetAuthor() throws Exception {
        assertEquals("Authors should be equal",AUTHOR,b.getAuthor());
    }

    @Test
    public void testSetAuthor() throws Exception {
        b.setAuthor(NEW_AUTHOR);
        assertEquals("Authors should be equal",NEW_AUTHOR,b.getAuthor());
    }



}
