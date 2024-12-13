package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest
{
    private static final String INIT_STATUS = "Request sent.";
    private static final String NEW_STATUS = "Blood en route.";
    private static final BloodEnum NEW_BLOOD = BloodEnum.B3p;
    private static final Integer NEW_URGENCY = 3;
    private static final Doctor NEW_DOCTOR = new Doctor();

    private BloodEnum blood;
    private Integer urgency;
    private Doctor doctor;

    private Request request;


    @Before
    public void setUp() throws Exception
    {
        blood = BloodEnum.AB4p;
        urgency = 1;
        doctor = new Doctor();

        request = new Request(blood, urgency, doctor);

    }

    @After
    public void tearDown() throws Exception
    {
        blood = null;
        urgency = null;
        doctor = null;

        request = null;
    }

    @Test
    public void getBlood()
    {
        assertEquals(request.getBlood(), BloodEnum.AB4p);
    }

    @Test
    public void getUrgency()
    {
        assertEquals(request.getUrgency(), urgency);
    }

    @Test
    public void getDoctor()
    {
        assertEquals(request.getDoctor(), doctor);
    }

    @Test
    public void getStatus()
    {
        assertEquals(request.getStatus(), INIT_STATUS);
    }

    @Test
    public void setBlood()
    {
        request.setBlood(NEW_BLOOD);
        assertEquals(request.getBlood(), NEW_BLOOD);
    }

    @Test
    public void setUrgency()
    {
        request.setUrgency(NEW_URGENCY);
        assertEquals(request.getUrgency(), NEW_URGENCY);
    }

    @Test
    public void setDoctor()
    {
        request.setDoctor(NEW_DOCTOR);
        assertEquals(request.getDoctor(), NEW_DOCTOR);
    }

    @Test
    public void setStatus()
    {
        request.setStatus(NEW_STATUS);
        assertEquals(request.getStatus(), NEW_STATUS);
    }
}