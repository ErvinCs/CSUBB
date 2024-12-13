package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doningcenters")
public class DoningCenter extends BaseEntity<Long>
{
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dcpmember_id")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private DCPMember dcpMember;



    @Column
    private String address;

    /**
     * @param address: Address
     */
    public DoningCenter(String address)
    {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DCPMember getDcpMember() {
        return dcpMember;
    }

    public void setDcpMember(DCPMember dcpMember) {
        this.dcpMember = dcpMember;
    }

    @Override
    public String toString()
    {
        return "DoningCenter{id=" + this.getId() + "; " + address.toString() + "}";
    }
}