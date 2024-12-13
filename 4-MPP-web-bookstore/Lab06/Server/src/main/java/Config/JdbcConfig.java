package Config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;

@Configuration
public class JdbcConfig {

    @Bean
    JdbcOperations jdbcOperations() {return new JdbcTemplate(dataSource());}

    @Bean
    DataSource dataSource()
    {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/Bookstore");
        ds.setUsername("postgres");
        ds.setPassword("1234");

        return ds;
    }
}
