package Repository.DBRepository;

import Domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Arrays;
import java.util.List;

public class ClientDBRepository implements ClientRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM Clients";

        return
                jdbcOperations.query(sql, (rs, i)-> {
                    Long id = rs.getLong("id");
                    String name = rs.getString("client_name");
                    String country = rs.getString("country");
                    Client c = new Client(name, country);
                    c.setID(id);
                    return c;
                });
    }

    @Override
    public String add(String item) {
        String sql = "insert into Clients(client_name, country) values (?,?)";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, list.get(1), list.get(2));

        return x.toString();
    }

    @Override
    public String update(String item) {
        String sql ="update Client set client_name=?, country=? where id=?";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, list.get(1), list.get(2), Long.parseLong(list.get(3)));

        return x.toString();
    }

    @Override
    public String delete(Long id) {
        String sql = "delete from Clients where id=?";
        Integer x = jdbcOperations.update(sql, id);

        return x.toString();
    }
}
