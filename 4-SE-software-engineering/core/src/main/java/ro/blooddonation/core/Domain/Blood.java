package ro.blooddonation.core.Domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Deprecated
 */
@Embeddable
public class Blood
{
    @Column
    private String rh;

    @Column
    private String type;

    /**
     * Default constructor
     */
    public Blood() {}

    /**
     * @param rh: String("+" or "-")
     * @param type: String("O1", "A2", "B3" or "AB4")
     */
    public Blood(String rh, String type) {
       this.rh = rh;
       this.type = type;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Blood{rh=" + this.rh + ", type=" + this.type + "}";
    }

}