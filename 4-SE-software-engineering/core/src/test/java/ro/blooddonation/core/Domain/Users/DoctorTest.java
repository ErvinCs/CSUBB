package ro.blooddonation.core.Domain.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.blooddonation.core.Domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DoctorTest
{
    private static final String NEW_ADDR = "New, New, New";
    private static final Hospital NEW_H = new Hospital(NEW_ADDR);
    private static final Account NEW_ACC = new Account("New", "New");

    private String firstName;
    private String lastName;
    private LocalDate bDay;
    private String address;
    private String residence;
    private Long iCNP;
    private Account acc;

    private Hospital hospital;
    private String hAddr;
    private List<Patient> patientList;
    Patient patient1;
    Patient patient2;

    private Doctor doctor;


    @Before
    public void setUp() throws Exception
    {
        hAddr = "hAddress, hTown, hCountry";
        hospital = new Hospital(hAddr);

        firstName = "first";
        lastName = "last";
        bDay = LocalDate.of(1997, 11, 12);
        address = "Address, Town, Country";
        residence = "currAddress, currTown, currCountry";
        acc = new Account("Person", "Pass");
        iCNP = new Long("1971111111111");

        patient1 = new Patient();
        patient2 = new Patient();
        patientList = new ArrayList<>();

        doctor = new Doctor(firstName, lastName, bDay, address, residence, iCNP, acc, hospital, patientList);
    }

    @After
    public void tearDown() throws Exception
    {
        hAddr = null;
        hospital = null;

        firstName = null;
        lastName = null;
        bDay = null;
        address = null;
        residence = null;
        acc = null;
        iCNP = null;

        patientList = null;
        patient1 = null;
        patient2 = null;

        doctor = null;
    }

    @Test
    public void getHospital() throws Exception
    {
        assertEquals(doctor.getHospital(), hospital);
    }

    @Test
    public void setHospital() throws Exception
    {
        doctor.setHospital(NEW_H);
        assertEquals(doctor.getHospital(), NEW_H);
    }

    @Test
    public void getAccount() throws Exception
    {
        assertEquals(doctor.getAccount(), acc);
    }

    @Test
    public void setAccount() throws Exception
    {
        doctor.setAccount(NEW_ACC);
        assertEquals(doctor.getAccount(), NEW_ACC);
    }

    @Test
    public void updateCredentials() throws Exception
    {
        doctor.updateCredentials(Optional.of(NEW_ACC.getUsername()), Optional.of(NEW_ACC.getPassword()));
        assertEquals(doctor.getAccount().getUsername(), NEW_ACC.getUsername());
        assertEquals(doctor.getAccount().getPassword(), NEW_ACC.getPassword());
    }

    @Test
    public void addPatient() throws Exception
    {
        doctor.addPatient(patient1);
        doctor.addPatient(patient2);
        assertEquals(doctor.getPatients().size(), 2);
    }

    @Test
    public void removePatient() throws Exception
    {
        doctor.addPatient(patient1);
        doctor.addPatient(patient2);
        doctor.removePatient(patient1);
        assertEquals(doctor.getPatients().size(), 1);
    }

}