package Repository.DBRepository;

import Domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.*;

public class BookDBRepository implements BookRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM Books";

        return
                jdbcOperations.query(sql, (rs, i)-> {
                    Long id = rs.getLong("id");
                    String name = rs.getString("title");
                    String author = rs.getString("author");
                    int isbn = rs.getInt("ISBN");

                    Book b = new Book(name, author, isbn);
                    b.setID(id);
                    return b;
                });
    }

    @Override
    public String add(String item) {
        String sql = "insert into Books(title, author, ISBN) values (?,?,?)";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, list.get(1), list.get(2), Integer.parseInt(list.get(3)));

        return x.toString();
    }

    @Override
    public String update(String item) {
        String sql ="update Books set title=?, author=?, ISBN=? where id=?";
        List<String> list = Arrays.asList(item.split(","));
        Integer x = jdbcOperations.update(sql, list.get(1), list.get(2), Integer.parseInt(list.get(3)), Long.parseLong(list.get(4)));

        return x.toString();
    }

    @Override
    public String delete(Long id) {
        String sql = "delete from Books where id=?";
        Integer x = jdbcOperations.update(sql, id);

        return x.toString();
    }
}
