package br.com.profcon.infrastructure.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    private final String url;
    private final String username;
    private final String password;

    public DataSourceConfig(
            @Value("${datasource.url}") final String url,
            @Value("${datasource.username}") final String username,
            @Value("${datasource.password}") final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(String.format("jdbc:postgresql://%s", url));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
