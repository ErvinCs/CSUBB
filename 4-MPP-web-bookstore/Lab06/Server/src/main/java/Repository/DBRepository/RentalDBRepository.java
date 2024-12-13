package Repository.DBRepository;

import Domain.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Arrays;
import java.util.List;

public class RentalDBRepository implements RentalRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Rental> findAll() {
        String sql = "SELECT * FROM Rentals";

        return
                jdbcOperations.query(sql, (rs, i)-> {
                    Long id = rs.getLong("id");
                    Long bid = rs.getLong("book_id");
                    Long cid = rs.getLong("client_id");
                    Rental r = new Rental(bid, cid);
                    r.setID(id);
                    return r;
                });
    }

    @Override
    public String add(String item) {
        String sql = "insert into Rentals(book_id, client_id) values (?,?)";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, Long.parseLong(list.get(1)), Long.parseLong(list.get(2)));

        return x.toString();
    }

    @Override
    public String update(String item) {
        String sql ="update Rentals set book_id=?, client_id=? where id=?";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, Long.parseLong(list.get(1)), Long.parseLong(list.get(2)), Long.parseLong(list.get(3)));

        return x.toString();
    }

    @Override
    public String delete(Long id) {
        String sql = "delete from Rentals where id=?";
        Integer x = jdbcOperations.update(sql, id);

        return x.toString();
    }
}