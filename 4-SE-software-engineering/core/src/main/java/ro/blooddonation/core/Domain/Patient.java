package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient extends Person {

//    @Embedded
//    @AttributeOverride(name = "type", column = @Column(name = "rh"))
//    private Blood blood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column
    private BloodEnum blood;

    @Column
    private Double requestedBloodQuantity;

    @Column
    private Integer urgency;

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
    public Patient(String firstName, String lastName, LocalDate bDay, String address, String residence, Long CNP,
                   Account account, Doctor doctor)
    {
        super(firstName, lastName, bDay, address, residence, CNP, account);
        this.doctor = doctor;
        this.blood = null;
        this.requestedBloodQuantity = null;
        this.urgency = null;
    }

    public BloodEnum getBlood() {
        return blood;
    }

    public void setBlood(BloodEnum blood) {
        this.blood = blood;
    }

    public Double getRequestedBloodQuantity() {
        return requestedBloodQuantity;
    }

    public void setRequestedBloodQuantity(Double requestedBloodQuantity) {
        this.requestedBloodQuantity = requestedBloodQuantity;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}