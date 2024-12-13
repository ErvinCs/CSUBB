package Repository.DBRepository;

import Domain.BaseEntity;
import Domain.Book;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;
import Repository.InMemoryRepository;
import java.sql.*;
import java.util.*;

public class BookDBRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {

    private static String URL;
    private static String user;
    private static String password;

    public BookDBRepository(Validator<T> validator, String URL, String user, String password)
    {
        super(validator);
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    public Set<T> findAll()
    {
        Set<T> books = new HashSet<>();
        String sql = "select * from Books";
        try(Connection connection = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery())
        {
            while (rs.next())
            {
                Long id = rs.getLong("id");
                String name = rs.getString("title");
                String author = rs.getString("author");
                int isbn = rs.getInt("ISBN");
                Book b = new Book(name, author, isbn);
                b.setID(id);
                books.add((T) b);
            }
            return books;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public void saveRec(Book book)
    {
        String sql = "insert into Books(title, author, ISBN) values (?,?,?)";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3,book.getISBN());
            statement.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateRec(Book book)
    {
        String sql = "update Books set title=?, author=?, ISBN=? where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getISBN());
            statement.setLong(4,book.getID());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteById(Long bookId)
    {
        String sql = "delete from Books where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement st = con.prepareStatement(sql))
        {
            st.setLong(1, bookId);
            st.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
