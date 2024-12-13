package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor extends Person
{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany( mappedBy = "doctor",
                cascade = CascadeType.ALL,
                orphanRemoval = true )
    private List<Patient> patients;

    /**
     *
     * @param firstName
     * @param lastName
     * @param bDay
     * @param address
     * @param residence
     * @param CNP
     * @param account
     * @param hospital
     */
//    public Doctor(String firstName, String lastName, LocalDate bDay, String address, String residence,
//                  Long CNP, Account account, Hospital hospital)
//    {
//        super(firstName, lastName, bDay, address, residence, CNP, account);
//        this.hospital = hospital;
//        this.patients = new ArrayList<Patient>();
//    }
    public Doctor(String firstName, String lastName, LocalDate bDay, String address, String residence,
                  Long CNP, Account account, Hospital hospital, List<Patient> patients)
    {
        super(firstName, lastName, bDay, address, residence, CNP, account);
        this.hospital = hospital;
        this.patients = patients;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param residence
     * @param hospital
     */
    public void updateData(Optional<String> firstName, Optional<String> lastName, Optional<String> address, Optional<String> residence, Optional<Hospital> hospital)
    {
        super.updateData(firstName, lastName, address, residence);
        Hospital h = hospital.isPresent() ? this.hospital = hospital.get() : this.hospital;
    }

    public void addPatient(Patient patient)
    {
        this.patients.add(patient);
        patient.setDoctor(this);
    }

    public void removePatient(Patient patient)
    {
        this.patients.remove(patient);
        patient.setDoctor(null);
    }

}