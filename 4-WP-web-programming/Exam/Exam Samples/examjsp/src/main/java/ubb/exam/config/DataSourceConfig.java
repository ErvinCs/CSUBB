package ubb.exam.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfig {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/exam");
        config.setUsername("root");
        config.setPassword("");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    private DataSourceConfig() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
