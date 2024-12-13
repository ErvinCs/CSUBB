package Repository.DBRepository;

import Domain.BaseEntity;
import Domain.Client;
import Domain.Rental;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RentalDBRepository<ID, T extends BaseEntity<ID>> extends Repository.InMemoryRepository<ID, T> {

    private static String URL;
    private static String user;
    private static String password;

    public RentalDBRepository(Validator<T> validator, String URL, String user, String password)
    {
        super(validator);
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    public Set<T> findAll()
    {
        Set<T> rentals = new HashSet<>();
        String sql = "select * from Rentals";
        try(Connection connection = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery())
        {
            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long bid = rs.getLong("book_id");
                Long cid = rs.getLong("client_id");
                Rental r = new Rental(bid, cid);
                r.setID(id);
                rentals.add((T) r);
            }
            return rentals;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public void saveRec(Rental rental)
    {
        String sql = "insert into Rentals(book_id, client_id) values (?,?)";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setLong(1, rental.getBookID());
            statement.setLong(2, rental.getClientID());
            statement.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateRec(Rental rental)
    {
        String sql = "update Rentals set book_id=?, client_id=? where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setLong(1, rental.getBookID());
            statement.setLong(2, rental.getClientID());
            statement.setLong(3, rental.getID());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteById(Long rentalId)
    {
        String sql = "delete from Rentals where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement st = con.prepareStatement(sql))
        {
            st.setLong(1, rentalId);
            st.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
