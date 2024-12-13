package bookstore.angular.Model;

import javax.persistence.*;


@Entity
@Table(name = "rentals")
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookid;
    private Long clientid;


    public Long getId() {
        return id;
    }

    public void setId(Long rId) {
        this.id = rId;
    }

    public Long getBookID() {
        return bookid;
    }

    public void setBookID(Long bookID) {
        this.bookid = bookID;
    }

    public Long getClientID() {
        return clientid;
    }

    public void setClientID(Long clientID) {
        this.clientid = clientID;
    }

    @Override
    public String toString()
    {
        return "Rental{" +
                "ID=" + getId() +
                ", bookID='" + bookid + '\'' +
                ", clientID='" + clientid + '\'' +
                '}';
    }
}
