package bookstore.angular.Model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;


    public Long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setID(Long aLong) {
        this.id = aLong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
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
