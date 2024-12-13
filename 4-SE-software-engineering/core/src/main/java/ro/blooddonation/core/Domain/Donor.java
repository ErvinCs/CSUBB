package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donors")
public class Donor extends Person {

    @Column
    private Donation currDonation;

    @Column
    private LocalDate currAppointment;

    @Column
    private LocalDate lastDonation;

    @Column
    @ElementCollection
    private List<Donation> donationHistory;

    /**
     *
     * @param firstName: String
     * @param lastName: String
     * @param bDay: LocalDate
     * @param address: Address
     * @param residence: Address
     * @param CNP: Long
     * @param account: Account
     */
    public Donor(String firstName, String lastName, LocalDate bDay, String address, String residence, Long CNP,
                 Account account)
    {
        super(firstName, lastName, bDay, address, residence, CNP, account);
        this.donationHistory = new ArrayList<Donation>();
        this.currDonation = null;
        this.currAppointment = null;
        this.lastDonation = null;

    }

    public Donation getCurrDonation() {
        return currDonation;
    }

    public void setCurrDonation(Donation currDonation) {
        this.currDonation = currDonation;
    }

    public LocalDate getCurrAppointment() {
        return currAppointment;
    }

    public void setCurrAppointment(LocalDate currAppointment) {
        this.currAppointment = currAppointment;
    }

    public LocalDate getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(LocalDate lastDonation) {
        this.lastDonation = lastDonation;
    }

    public List<Donation> getDonationHistory()
    {
        return this.donationHistory;
    }

    public void setDonationHistory(List<Donation> donationHistory) {
        this.donationHistory = donationHistory;
    }


    /**
     * If the history already contains donation, then update it, otherwise add donation to the history.
     * @param donation
     */
    public void updateDonationHistory(Donation donation)
    {
        for (Donation don : donationHistory)
        {
            if (don.getId().equals(donation.getId()))
            {
                donationHistory.set(donationHistory.indexOf(donation), donation);
                return;
            }
        }
        this.donationHistory.add(donation);
    }
}