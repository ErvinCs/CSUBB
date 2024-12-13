package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Client extends BaseEntity<Long> implements Serializable
{
    private String name;
    private String country;

    public Client(String name,String country)
    {
        this.name = name;
        this.country = country;
    }

    @Override
    public Long getID() {
        return super.getID();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void setID(Long aLong) {
        super.setID(aLong);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(country, client.country);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + getID() +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
