package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RentalTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final Long BOOKID = 10L;
    private static final Long NEW_BOOKID= 20L;
    private static final Long CLIENTID = 100L;
    private static final Long NEW_CLIENTID= 200L;

    private Rental r;

    @Before
    public void setUp() {
        r = new Rental(BOOKID, CLIENTID);
        r.setID(ID);
    }

    @After
    public void tearDown() {
        r = null;
    }

    @Test
    public void testGetBookID() {
        assertEquals(BOOKID, r.getBookID());
    }

    @Test
    public void testSetBookID() {
        r.setBookID(NEW_BOOKID);
        assertEquals(NEW_BOOKID, r.getBookID());
    }

    @Test
    public void testGetClientID() {
        assertEquals(CLIENTID, r.getClientID());
    }

    @Test
    public void testSetClientID() {
        r.setClientID(NEW_CLIENTID);
        assertEquals(NEW_CLIENTID, r.getClientID());
    }

    @Test
    public void testEquals() {
        Rental r2 = new Rental(BOOKID, CLIENTID);
        assertEquals(r, r2);
    }

    @Test
    public void testToString() {
        assertEquals(r.toString(), "Rental{" + "ID=" + r.getID() +
                ", bookID='" + r.getBookID() + '\'' +
                ", clientID='" + r.getClientID() + '\'' +
                '}');
    }

}