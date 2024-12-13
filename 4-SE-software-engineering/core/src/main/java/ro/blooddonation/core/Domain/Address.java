package ro.blooddonation.core.Domain;

import javax.persistence.*;

/**
 * Deprecated
 */
@Embeddable
public class Address
{
    private String address;

    private String town;

    private String country;

    /**
     * Default constructor
     */
    public Address() {}

    /**
     *
     * @param address: String
     * @param town: String
     * @param country: String
     */
    public Address(String address, String town, String country)
    {
        this.address = address;
        this.town = town;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "Address{address=" + address + ", town=" + town
                + ", country=" + country + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Address)) {
            return false;
        }

        Address address = (Address) o;
        return this.getAddress() == ((Address) o).getAddress() && this.getCountry() == ((Address) o).getCountry() &&
                this.getTown() == ((Address) o).getTown();

    }
}