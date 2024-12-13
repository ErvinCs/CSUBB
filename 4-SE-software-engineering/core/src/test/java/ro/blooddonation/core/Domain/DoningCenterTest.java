package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoningCenterTest
{
    private static final String NEW_ADDR = "New, New, New";

    private DCPMember dcpMember;
    private String address;

    private DoningCenter dc;

    @Before
    public void setUp() throws Exception
    {
        dcpMember = new DCPMember();
        address = "Street, Town, Country";
        dc = new DoningCenter(address);
    }

    @After
    public void tearDown() throws Exception
    {
        dcpMember = null;
        address = null;
        dc = null;
    }

    @Test
    public void getAddress()
    {
        assertEquals(dc.getAddress(), address);
    }

    @Test
    public void setAddress()
    {
        dc.setAddress(NEW_ADDR);
        assertEquals(dc.getAddress(), NEW_ADDR);
    }

    @Test
    public void getDcpMember()
    {
        assertNull(dc.getDcpMember());
    }

    @Test
    public void setDcpMember()
    {
        dc.setDcpMember(dcpMember);
        assertEquals(dc.getDcpMember(), dcpMember);
    }
}