package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.Nastaveni;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import java.sql.SQLException;

public class MariaDbKonfig {

    public MariaDbDataSource getKonfiguraceDatabaze() {
        return konfiguraceDatabaze;
    }

    public void setKonfiguraceDatabaze(MariaDbDataSource konfiguraceDatabaze) {
        this.konfiguraceDatabaze = konfiguraceDatabaze;
    }

    private MariaDbDataSource konfiguraceDatabaze;

    public MariaDbKonfig() {
        try {
            konfiguraceDatabaze = new MariaDbDataSource();

            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/robot_one");

        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit konfiguraci Databaze", e);
        }
    }
}
