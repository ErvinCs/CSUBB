package ro.blooddonation.core.Domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request extends BaseEntity<Long>
{
//    @Embedded
//    @AttributeOverride(name = "type", column = @Column(name = "rh"))
//    private Blood blood;

    @Enumerated(EnumType.STRING)
    @Column
    private BloodEnum blood;

    @Column
    private Integer urgency;

    @Column
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Doctor doctor;


    /**
     *
     * @param blood
     * @param urgency
     * @param doctor
     */
    public Request(BloodEnum blood, Integer urgency, Doctor doctor)
    {
        this.blood = blood;
        this.urgency = urgency;
        this.doctor = doctor;
        this.status = "Request sent.";
    }

    public BloodEnum getBlood() {
        return blood;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setBlood(BloodEnum blood) {
        this.blood = blood;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Request{id=" + this.getId() +
                ", blood=" + this.getBlood().toString() +
                ", urgency=" + this.getUrgency().toString() +
                ", status=" + this.getStatus().toString() +
                ", fromDoctor(id)=" + this.getDoctor().getId().toString() + "}";
    }
}
