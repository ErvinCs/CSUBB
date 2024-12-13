package ro.blooddonation.core.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DonationTest {

    private static final Double NEW_QUANTITY = 1000.0;
    private static final LocalDate NEW_DATE = LocalDate.of(2020, 2, 2);
    private static final BloodEnum INIT_BLOOD = BloodEnum.O1n;
    private static final BloodEnum NEW_BLOOD = BloodEnum.O1p;

    private BloodEnum blood;
    private Double bloodQuantity;

    private Double plasmaQuantity;
    private Double thrombocytesQuantity;
    private Double redCellsQuantity;

    private LocalDate donationDate;

    private Donation donation;

    @Before
    public void setUp() throws Exception
    {
        blood = INIT_BLOOD;
        bloodQuantity = 500.0;

        plasmaQuantity = 100.0;
        thrombocytesQuantity = 150.0;
        redCellsQuantity = 250.0;

        donationDate = LocalDate.of(2018, 2, 2);

        donation = new Donation(donationDate, bloodQuantity);
    }

    @After
    public void tearDown() throws Exception
    {
        blood = null;
        bloodQuantity = null;

        plasmaQuantity = null;
        thrombocytesQuantity = null;
        redCellsQuantity = null;

        donationDate = null;

        donation = null;
    }

    @Test
    public void getDonationDate()
    {
        assertEquals(donation.getDonationDate(), donationDate);
    }

    @Test
    public void setDonationDate()
    {
        donation.setDonationDate(NEW_DATE);
        assertEquals(donation.getDonationDate(), NEW_DATE);
    }

    @Test
    public void getBloodQuantity()
    {
        assertEquals(donation.getBloodQuantity(), bloodQuantity);
    }

    @Test
    public void setBloodQuantity()
    {
        donation.setBloodQuantity(NEW_QUANTITY);
        assertEquals(donation.getBloodQuantity(), NEW_QUANTITY);
    }

    @Test
    public void getBlood()
    {
        assertNull(donation.getBlood());
    }

    @Test
    public void setBlood()
    {
        donation.setBlood(blood);
        assertEquals(donation.getBlood(), blood);
    }


    @Test
    public void getPlasmaQuantity()
    {
        assertNull(donation.getPlasmaQuantity());
    }

    @Test
    public void setPlasmaQuantity()
    {
        donation.setPlasmaQuantity(plasmaQuantity);
        assertEquals(donation.getPlasmaQuantity(), plasmaQuantity);
    }

    @Test
    public void getThrombocytesQuantity()
    {
        assertNull(donation.getThrombocytesQuantity());
    }

    @Test
    public void setThrombocytesQuantity()
    {
        donation.setThrombocytesQuantity(thrombocytesQuantity);
        assertEquals(donation.getThrombocytesQuantity(), thrombocytesQuantity);
    }

    @Test
    public void getRedCellsQuantity()
    {
        assertNull(donation.getRedCellsQuantity());
    }

    @Test
    public void setRedCellsQuantity()
    {
        donation.setRedCellsQuantity(redCellsQuantity);
        assertEquals(donation.getRedCellsQuantity(), redCellsQuantity);
    }

    @Test
    public void getDiseases()
    {
        assertEquals(donation.getDiseases().size(), 13);
        donation.getDiseases().forEach((disease, value) -> assertEquals(value, false));
    }

    @Test
    public void setDiseases()
    {
        donation.getDiseases().forEach((disease, value) -> donation.setDisease(disease, true));
        donation.getDiseases().forEach((disease, value) -> assertEquals(value, true));
    }

    @Test
    public void hasDisease()
    {
        assertFalse(donation.hasDisease());
        donation.getDiseases().forEach((disease, value) -> donation.setDisease(disease, true));
        assertTrue(donation.hasDisease());

        donation.getDiseases().forEach((disease, value) -> donation.setDisease(disease, false));
        donation.setDisease(DiseasesEnum.HeartDisease, true);
        assertTrue(donation.hasDisease());
    }
}