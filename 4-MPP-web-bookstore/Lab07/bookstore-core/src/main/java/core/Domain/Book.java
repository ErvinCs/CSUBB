package core.Domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
//@Table(name = "books")
public class Book extends BaseEntity<Long>
{
    //@Column
    private String name;

    //@Column
    private String author;

    //@Column
    private int ISBN;

    public Book(String name, String author, int ISBN) {
        this.name = name;
        this.author = author;
        this.ISBN = ISBN;
    }

    @Override
    public Long getID() {
        return super.getID();
    }

    @Override
    public void setID(Long aLong) {
        super.setID(aLong);
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return ISBN == book.ISBN &&
                Objects.equals(name, book.name) &&
                Objects.equals(author, book.author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + getID() +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                '}';
    }

}
