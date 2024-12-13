package ro.blooddonation.core.Domain.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.blooddonation.core.Domain.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PatientTest
{
    public static final Integer NEW_URGENCY = 3;
    public static final Double NEW_REQ_B_QUANTITY = 100.0;
    public static final BloodEnum BLOOD_ENUM = BloodEnum.A2n;

    private String firstName;
    private String lastName;
    private LocalDate bDay;
    private String address;
    private String residence;
    private Long iCNP;
    private Account account;

    private Doctor doctor;

    private Patient patient;

    @Before
    public void setUp() throws Exception
    {
        firstName = "first";
        lastName = "last";
        bDay = LocalDate.of(1997, 11, 12);
        address = "Address, Town, Country";
        residence = "currAddress, currTown, currCountry";
        iCNP = new Long("1971111111111");
        account = new Account("Person", "Pass");

        doctor = new Doctor();

        patient = new Patient(firstName, lastName, bDay, address, residence, iCNP, account, doctor);
    }

    @After
    public void tearDown() throws Exception
    {
        firstName = null;
        lastName = null;
        bDay = null;
        address = null;
        residence = null;
        iCNP = null;
        account = null;

        doctor = null;

        patient = null;
    }

    @Test
    public void getBlood() throws Exception
    {
        assertNull(patient.getBlood());
    }

    @Test
    public void setBlood() throws Exception
    {
        patient.setBlood(BLOOD_ENUM);
        assertEquals(patient.getBlood(), BLOOD_ENUM);
    }

    @Test
    public void getRequestedBloodQuantity() throws Exception
    {
        assertNull(patient.getRequestedBloodQuantity());
    }

    @Test
    public void setRequestedBloodQuantity() throws Exception
    {
        patient.setRequestedBloodQuantity(NEW_REQ_B_QUANTITY);
        assertEquals(patient.getRequestedBloodQuantity(), NEW_REQ_B_QUANTITY);
    }

    @Test
    public void getUrgency() throws Exception
    {
        assertNull(patient.getUrgency());
    }

    @Test
    public void setUrgency() throws Exception
    {
        patient.setUrgency(NEW_URGENCY);
        assertEquals(patient.getUrgency(), NEW_URGENCY);
    }

}