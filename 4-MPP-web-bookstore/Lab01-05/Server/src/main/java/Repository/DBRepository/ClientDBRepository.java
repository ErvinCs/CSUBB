package Repository.DBRepository;

import Domain.BaseEntity;
import Domain.Client;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDBRepository<ID, T extends BaseEntity<ID>> extends Repository.InMemoryRepository<ID, T> {

    private static String URL;
    private static String user;
    private static String password;

    public ClientDBRepository(Validator<T> validator, String URL, String user, String password)
    {
        super(validator);
        this.URL = URL;
        this.user = user;
        this.password = password;
    }

    public Set<T> findAll()
    {
        Set<T> clients = new HashSet<>();
        String sql = "select * from Clients";
        try(Connection connection = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery())
        {
            while (rs.next())
            {
                Long id = rs.getLong("id");
                String name = rs.getString("client_name");
                String country = rs.getString("country");
                Client c = new Client(name, country);
                c.setID(id);
                clients.add((T) c);
            }
            return clients;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public void saveRec(Client client)
    {
        String sql = "insert into Clients(client_name, country) values (?,?)";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setString(1, client.getName());
            statement.setString(2, client.getCountry());
            statement.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateRec(Client client)
    {
        String sql = "update Client set client_name=?, country=? where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement statement = con.prepareStatement(sql))
        {
            statement.setString(1, client.getName());
            statement.setString(2, client.getCountry());
            statement.setLong(3, client.getID());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteById(Long clientId)
    {
        String sql = "delete from Clients where id=?";
        try(Connection con = DriverManager.getConnection(URL, user, password);
            PreparedStatement st = con.prepareStatement(sql))
        {
            st.setLong(1, clientId);
            st.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
