package ro.blooddonation.core.Domain.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.blooddonation.core.Domain.Account;
import ro.blooddonation.core.Domain.Address;
import ro.blooddonation.core.Domain.DCPMember;
import ro.blooddonation.core.Domain.DoningCenter;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class DCPMemberTest
{
    private static final String NEW_ADDR = "New, New, New";
    private static final DoningCenter NEW_DC = new DoningCenter(NEW_ADDR);
    private static final Account NEW_ACC = new Account("New", "New");

    private String firstName;
    private String lastName;
    private LocalDate bDay;
    private String address;
    private String residence;
    private Long iCNP;
    private Account acc;

    private DoningCenter doningCen;
    private String dcAddr;

    private DCPMember dcpMem;



    @Before
    public void setUp() throws Exception
    {
        dcAddr = "dcAddress, dcTown, dcCountry";
        doningCen = new DoningCenter(dcAddr);

        firstName = "first";
        lastName = "last";
        bDay = LocalDate.of(1997, 11, 12);
        address = "Address, Town, Country";
        residence = "currAddress, currTown, currCountry";
        acc = new Account("Person", "Pass");
        iCNP = new Long("1971111111111");

        dcpMem = new DCPMember(firstName, lastName, bDay, address, residence, iCNP, acc, doningCen);
    }

    @After
    public void tearDown() throws Exception
    {
        dcAddr = null;
        doningCen = null;

        firstName = null;
        lastName = null;
        bDay = null;
        address = null;
        residence = null;
        acc = null;
        iCNP = null;

        dcpMem = null;
    }

    @Test
    public void getCNP() throws Exception
    {
        assertEquals(dcpMem.getCNP(), iCNP);
    }

    @Test
    public void getDoningCenter() throws Exception
    {
        assertEquals(dcpMem.getDoningCenter(), doningCen);
    }

    @Test
    public void setDoningCenter() throws Exception
    {
        dcpMem.setDoningCenter(NEW_DC);
        assertEquals(dcpMem.getDoningCenter(), NEW_DC);
    }

    @Test
    public void getAccount() throws Exception
    {
        assertEquals(dcpMem.getAccount(), acc);
    }

    @Test
    public void setAccount() throws Exception
    {
        dcpMem.setAccount(NEW_ACC);
        assertEquals(dcpMem.getAccount(), NEW_ACC);
    }

    @Test
    public void updateCredentials() throws Exception
    {
        dcpMem.updateCredentials(Optional.of(NEW_ACC.getUsername()), Optional.of(NEW_ACC.getPassword()));
        assertEquals(dcpMem.getAccount().getUsername(), NEW_ACC.getUsername());
        assertEquals(dcpMem.getAccount().getPassword(), NEW_ACC.getPassword());
    }

}