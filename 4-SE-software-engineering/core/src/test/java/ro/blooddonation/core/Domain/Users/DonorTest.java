package ro.blooddonation.core.Domain.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.blooddonation.core.Domain.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DonorTest
{
    private static final Double QUANTITY = 500.0;
    private static final LocalDate NEW_DATE =  LocalDate.of(2018, 1, 1);
    private static final Donation NEW_DON = new Donation(NEW_DATE, QUANTITY);
    private static final BloodEnum BLOOD_ENUM = BloodEnum.O1p;

    private String firstName;
    private String lastName;
    private LocalDate bDay;
    private String address;
    private String residence;
    private Long iCNP;
    private Account account;

    private Donation donation;

    private Donor donor;

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

        donation = new Donation();

        donor = new Donor(firstName, lastName, bDay, address, residence, iCNP, account);
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

        donation = null;

        donor = null;
    }

    @Test
    public void getCurrDonation() throws Exception
    {
        assertNull(donor.getCurrDonation());
    }

    @Test
    public void setCurrDonation() throws Exception
    {
        donor.setCurrDonation(NEW_DON);
        //throws exception; setCurrDonation should only allow donations in the list to be set
    }

    @Test
    public void getCurrAppointment() throws Exception
    {
        assertNull(donor.getCurrAppointment());
    }

    @Test
    public void setCurrAppointment() throws Exception
    {
        donor.setCurrAppointment(NEW_DATE);
        assertEquals(donor.getCurrAppointment(), NEW_DATE);
    }

    @Test
    public void getLastDonation() throws Exception
    {
        assertNull(donor.getLastDonation());
        donor.updateDonationHistory(donation);
        assertEquals(donor.getLastDonation(), donation.getDonationDate());
        //getLastDonation should return the donation not its date
    }

    @Test
    public void setLastDonation() throws Exception
    {
        donor.setLastDonation(NEW_DATE);
        //throws exception; setLastDonation should only allow donations in the list to be set or should be called automatically
    }

    @Test
    public void getDonationHistory() throws Exception
    {
        assertEquals(donor.getDonationHistory().size(), 0);

        donor.updateDonationHistory(donation);

        assertEquals(donor.getDonationHistory().size(), 1);
    }

    @Test
    public void updateDonationHistory() throws Exception
    {
        donor.updateDonationHistory(donation);
        assertEquals(donor.getDonationHistory().size(), 1);
        assertEquals(donor.getDonationHistory().get(0), donation);

        donation.setBlood(BLOOD_ENUM);
        donation.setRedCellsQuantity(100.0);

        assertEquals(donor.getDonationHistory().size(), 1);
        assertEquals(donor.getDonationHistory().get(0), donation);
        //add another donation (needs id)
    }
}