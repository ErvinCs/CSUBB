package ubb.exam.repository;

import ubb.exam.config.DataSourceConfig;
import ubb.exam.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IRepository<User> {
    private final String table_name = "users";

    public UserRepository() {}

    private User parseUser(ResultSet rs) throws SQLException {
        Long id = rs.getLong("user_id");
        String username = rs.getString("username");
        String pass = rs.getString("password");

        User user = new User(username, pass);
        user.setId(id);

        return user;
    }


    @Override
    public Optional<User> getById(Long id) {
        String sql = "SELECT * FROM " + table_name + " WHERE user_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                User user = this.parseUser(rs);
                connection.close();
                return Optional.of(user);
            }
            else {
                connection.close();
                return Optional.empty();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> getByCredentials(String username, String password) {
        String sql = "SELECT * FROM " + table_name + " WHERE username=? AND password=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                User user = this.parseUser(rs);
                connection.close();
                return Optional.of(user);
            }
            else {
                connection.close();
                return Optional.empty();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> getByUsername(String username) {
        String sql = "SELECT * FROM " + table_name + " WHERE username=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                User user = this.parseUser(rs);
                connection.close();
                return Optional.of(user);
            }
            else {
                connection.close();
                return Optional.empty();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name;
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = this.parseUser(rs);
                userList.add(user);
            }
            connection.close();
            return userList;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public void add(User user) {
        String sql = "INSERT INTO " + table_name + " (username, password) VALUES (?,?)";

        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM " + table_name + " WHERE user_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void update(User user) {
        String sql = "UPDATE " + table_name + " set username=?, password=? where user_id=?";
        try(Connection connection = DataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
