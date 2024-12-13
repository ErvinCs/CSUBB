package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {

    private String password;
    private String username;

    private Admin admin;

    @Before
    public void setUp() throws Exception
    {
        password = "T0XP";
        username = "@dmin";

        admin = new Admin(username, password);
    }

    @After
    public void tearDown() throws Exception
    {
        password = null;
        username = null;

        admin = null;
    }

    @Test
    public void getUsername()
    {
        assertEquals(admin.getUsername(), "@dmin");
    }

    @Test
    public void getPassword()
    {
        assertEquals(admin.getPassword(), "T0XP");
    }
}