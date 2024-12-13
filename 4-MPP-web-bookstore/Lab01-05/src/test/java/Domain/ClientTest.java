package Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String NAME = "Book1";
    private static final String NEW_NAME="Book2";
    private static final String COUNTRY = "C1";
    private static final String NEW_COUNTRY="C2";

    private Client c;

    @Before
    public void setUp() throws Exception {
        c = new Client(NAME, COUNTRY);
        c.setID(ID);
    }

    @After
    public void tearDown() throws Exception {
        c = null;
    }


    @Test
    public void testGetId() throws Exception {
        assertEquals("Ids should be equal", ID, c.getID());
    }

    @Test
    public void testSetId() throws Exception {
        c.setID(NEW_ID);
        assertEquals("Ids should be equal", NEW_ID, c.getID());
    }


    @Test
    public void testGetName() throws Exception {
        assertEquals("Names should be equal",NAME,c.getName());
    }

    @Test
    public void testSetName() throws Exception {
        c.setName(NEW_NAME);
        assertEquals("Names should be equal",NEW_NAME,c.getName());
    }

    @Test
    public void testGetCountry() throws Exception {
        assertEquals("Countries should be equal",COUNTRY,c.getCountry());
    }

    @Test
    public void testSetCountry() throws Exception {
        c.setCountry(NEW_COUNTRY);
        assertEquals("Countries should be equal",NEW_COUNTRY,c.getCountry());
    }



}
