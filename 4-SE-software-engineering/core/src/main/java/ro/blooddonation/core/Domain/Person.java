package ro.blooddonation.core.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@MappedSuperclass
public abstract class Person extends BaseEntity<Long> implements Serializable
{
    @Column
    private Long CNP;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate bDay;

    @Column
    private String address;

    @Column
    private String residence;

    @Column
    private Account account;

    /**
     * Default constructor
     */
    protected Person() {}

    /**
     * @param firstName: String
     * @param lastName: String
     * @param bDay: LocalDate
     * @param address: String
     * @param residence: String
     * @param CNP: Long
     * @param account: Account
     */
    protected Person(String firstName, String lastName, LocalDate bDay, String address, String residence,
                     Long CNP, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bDay = bDay;
        this.address = address;
        this.residence = residence;
        this.CNP = CNP;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return CNP - Long
     */
    public Long getCNP()
    {
        return this.CNP;
    }

    public void setCNP(Long CNP) {
        this.CNP = CNP;
    }

    /**
     * @return bDay - LocalDate
     */
    public LocalDate getbDay()
    {
        return this.bDay;
    }

    public void setbDay(LocalDate bDay) {
        this.bDay = bDay;
    }

    /**
     * @return: the number of years between the current date and this Person's birthday - int
     */
    public Integer getAge()
    {
        LocalDate now = LocalDate.now();
        Period period = Period.between(this.bDay, now);
        return period.getYears();
    }

    /**
     * Update this Person's fields if the parameters are present.
     * @param firstName: String
     * @param lastName: String
     * @param address: Address
     * @param residence: Address
     */
    public void updateData(Optional<String> firstName, Optional<String> lastName, Optional<String> address, Optional<String> residence)
    {
        String fn = firstName.isPresent() ? this.firstName = firstName.get() : this.firstName;
        String ln = lastName.isPresent() ? this.lastName = lastName.get() : this.lastName;
        String addr = address.isPresent() ? this.address = address.get() : this.address;
        String res = residence.isPresent() ? this.residence = residence.get() : this.residence;
    }

    public void updateCredentials(Optional<String> username, Optional<String> password)
    {
        account.updateCredentials(username, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person ))
            return false;

        return CNP != null && CNP.equals(((Person) o).getCNP());
    }
}