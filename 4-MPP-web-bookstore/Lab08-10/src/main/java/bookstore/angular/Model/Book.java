package bookstore.angular.Model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int ISBN;


    public Long getID() {
        return id;
    }

    public void setID(Long aLong) {
        this.id = aLong;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }


    @Override
    public String toString() {
        return "Book{" +
                "ID=" + getID() +
                ", name='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                '}' + '\n';
    }
}
