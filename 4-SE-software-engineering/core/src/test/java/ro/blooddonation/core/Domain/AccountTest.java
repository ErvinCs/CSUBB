package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class AccountTest
{
    private static final String NEW_USERNAME = "NewUsername";
    private static final String NEW_PASSWORD = "NewPassword";
    private static final Donor NEW_OWNER = new Donor();

    private String username;
    private String password;
    private Person owner;
    private Doctor doctor;

    Account account;

    @Before
    public void setUp() throws Exception
    {
        username = "Username";
        password = "Password";
        doctor = new Doctor();
        owner = doctor;

        account = new Account(username, password, owner);
    }

    @After
    public void tearDown() throws Exception
    {
        username = null;
        password = null;
        doctor = null;

        owner = null;

        account = null;
    }

    @Test
    public void getUsername()
    {
        assertEquals(account.getUsername(), "Username");
    }

    @Test
    public void setUsername()
    {
        account.setUsername(NEW_USERNAME);
        assertEquals(account.getUsername(), "NewUsername");
    }

    @Test
    public void getPassword()
    {
        assertEquals(account.getPassword(), "Password");
    }

    @Test
    public void setPassword()
    {
        account.setPassword(NEW_PASSWORD);
        assertEquals(account.getPassword(), NEW_PASSWORD);
    }

    @Test
    public void getOwner()
    {
        assertEquals(account.getOwner(), doctor);

    }

    @Test
    public void setOwner()
    {
        account.setOwner(NEW_OWNER);
        assertEquals(account.getOwner(), NEW_OWNER);
    }

    @Test
    public void updateCredentials()
    {
        Optional<String> newUsername = Optional.of(NEW_USERNAME);
        Optional<String> newPasword = Optional.of(NEW_PASSWORD);

        account.updateCredentials(newUsername, newPasword);
        assertEquals(account.getUsername(), NEW_USERNAME);
        assertEquals(account.getPassword(), NEW_PASSWORD);
    }
}