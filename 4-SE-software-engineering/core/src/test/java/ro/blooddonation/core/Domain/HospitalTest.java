package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HospitalTest
{
    private Hospital h1;
    private String address1;
    private String address2;
    private Doctor doctor1;
    private Doctor doctor2;
    private List<Doctor> doctorsList1;
    private List<Doctor> doctorsList2;

    @Before
    public void setUp() throws Exception
    {
        address1 = "Street1, Town1, Country1";
        doctor1 = new Doctor();
        doctorsList1 = new ArrayList<>();
        h1 = new Hospital(address1, doctorsList1);
        address2 = "Street2, Town2, Country2";
        doctor2 = new Doctor();
        doctorsList2 = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception
    {
        address1 = null;
        doctor1 = null;
        doctorsList1 = null;
        h1 = null;
        address2 = null;
        doctor2 = null;
        doctorsList2 = null;
    }

    @Test
    public void getAddress() throws Exception
    {
        assertEquals(h1.getAddress(), address1);
    }

    @Test
    public void setAddress() throws Exception
    {
        assertEquals(h1.getAddress(), address1);
        h1.setAddress(address2);
        assertEquals(h1.getAddress(), address2);
    }

    @Test
    public void getId() throws Exception
    {
        System.out.println(h1.getId());
    }

    @Test
    public void addDoctor() throws Exception
    {
        h1.addDoctor(doctor1);
        h1.addDoctor(doctor2);
        assertEquals(h1.getDoctors().size(), 2);
        //DoctorExistsException
    }

    @Test
    public void removeDoctor() throws Exception
    {
        h1.addDoctor(doctor1);
        h1.addDoctor(doctor2);
        h1.removeDoctor(doctor1);
        assertEquals(h1.getDoctors().size(), 1);
        //DoctorExistsException
    }

}