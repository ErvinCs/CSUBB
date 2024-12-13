package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Rental extends BaseEntity<Long> implements Serializable
{
    private Long bookID;
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
