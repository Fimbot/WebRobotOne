package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.*;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcNastaveniRepository implements NastaveniRepository {

    private JdbcTemplate odesilacDotaz;
    private RowMapper<Nastaveni> prevodnik;

    public JdbcNastaveniRepository() {
        try {
            MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();

            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/robot_one");

            odesilacDotaz = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Nastaveni.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit DataSource", e);
        }
    }

    @Override
    public List<Nastaveni> findAll() {
        List<Nastaveni> vysledek = odesilacDotaz.query("SELECT * FROM Parametr", prevodnik);
        return vysledek;
    }

    @Override
    public Nastaveni findById(Long id) {
        Nastaveni vysledek = odesilacDotaz.queryForObject(
                "SELECT * FROM Parametr WHERE ID=?", prevodnik, id);
        if (vysledek == null) return new Nastaveni();
        else return vysledek;
    }

    @Override
    public void save(Nastaveni zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            pridej(zaznamKUlozeni);
        } else {
            updatuj(zaznamKUlozeni);
        }
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotaz.update(
                "DELETE FROM Parametr WHERE id = ?",
                id);
    }

    private void updatuj(Nastaveni zaznamKUlozeni) {
        odesilacDotaz.update(
                "UPDATE Parametr SET Klic = ?, Hodnota = ?, Popis = ? WHERE id = ?",
                zaznamKUlozeni.getKlic(),
                zaznamKUlozeni.getHodnota(),
                zaznamKUlozeni.getPopis(),
                zaznamKUlozeni.getId());
    }



    private void pridej(Nastaveni zaznamKPridani) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Parametr (Klic, Hodnota, Popis) " +
                "VALUES (?, ?, ?)";
        odesilacDotaz.update((Connection con1) -> {
                    PreparedStatement prikaz = con1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zaznamKPridani.getKlic());
                    prikaz.setString(2, zaznamKPridani.getHodnota());
                    prikaz.setString(3, zaznamKPridani.getPopis());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zaznamKPridani.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    }

}
