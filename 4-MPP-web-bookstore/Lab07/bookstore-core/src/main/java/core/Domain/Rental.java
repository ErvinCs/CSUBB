package core.Domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
//@Table(name = "rentals")
public class Rental extends BaseEntity<Long>
{
    //@Column
    private Long bookID;

    //@Column
    private Long clientID;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }


    public Rental(Long bookID, Long clientID)
    {
        this.bookID = bookID;
        this.clientID = clientID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental)) return false;
        Rental rental = (Rental) o;
        return Objects.equals(bookID, rental.bookID) &&
                Objects.equals(clientID, rental.clientID);
    }

    @Override
    public String toString()
    {
        return "Rental{" +
                "ID=" + getID() +
                ", bookID='" + bookID + '\'' +
                ", clientID='" + clientID + '\'' +
                '}';
    }
}
