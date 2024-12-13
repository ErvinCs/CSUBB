package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@Table(name = "dcpmembers")
public class DCPMember extends Person
{
//    @OneToOne(  mappedBy = "dcpmembers",
//                cascade = CascadeType.ALL,
//                fetch = FetchType.LAZY,
//                optional = false)

    private DoningCenter doningCenter;

    /**
     *
     * @param firstName
     * @param lastName
     * @param bDay
     * @param address
     * @param residence
     * @param CNP
     * @param account
     * @param doningCenter
     */
    public DCPMember(String firstName, String lastName, LocalDate bDay, String address, String residence,
                     Long CNP, Account account, DoningCenter doningCenter)
    {
        super(firstName, lastName, bDay, address, residence, CNP, account);
        this.doningCenter = doningCenter;
    }

    public DoningCenter getDoningCenter() {
        return doningCenter;
    }

    public void setDoningCenter(DoningCenter doningCenter) {
        this.doningCenter = doningCenter;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param residence
     * @param doningCenter
     */
    public void updateData(Optional<String> firstName, Optional<String> lastName, Optional<String> address, Optional<String> residence, Optional<DoningCenter> doningCenter)
    {
        super.updateData(firstName, lastName, address, residence);
        DoningCenter dc = doningCenter.isPresent() ? this.doningCenter = doningCenter.get() : this.doningCenter;
    }
}