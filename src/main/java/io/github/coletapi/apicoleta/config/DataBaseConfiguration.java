package io.github.coletapi.apicoleta.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("jdbc:postgresql://localhost:5434/coletasdb")
    String url;
    @Value("admin")
    String user;
    @Value("admin123")
    String pass;
    @Value("org.postgresql.Driver")
    String driver;

    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(pass);
        config.setDriverClassName(driver);
        return new HikariDataSource(config);
    }
}
