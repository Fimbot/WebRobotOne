package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.*;

import java.sql.*;
import java.util.*;

import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUzivatelRepository implements UzivatelRepository {

    private JdbcTemplate odesilacDotazu;
    private RowMapper<Uzivatel> prevodnik;

    public JdbcUzivatelRepository() {
        try {
            MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();

            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/robot_one");

            odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Uzivatel.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit DataSource", e);
        }
    }

    @Override
    public List<Uzivatel> findAll() {
        List<Uzivatel> vysledek = odesilacDotazu.query("SELECT * FROM Uzivatel", prevodnik);
        return vysledek;
    }

    @Override
    public Uzivatel findById(Long id) {
        Uzivatel vysledek = odesilacDotazu.queryForObject(
                "SELECT * FROM Uzivatel WHERE ID=?", prevodnik, id);
        if (vysledek == null) return new Uzivatel();
        else return vysledek;
    }

    @Override
    public void save(Uzivatel zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            pridej(zaznamKUlozeni);
        } else {
            updatuj(zaznamKUlozeni);
        }
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotazu.update(
                "DELETE FROM Uzivatel WHERE id = ?",
                id);
    }

    private void updatuj(Uzivatel zaznamKUlozeni) {
        odesilacDotazu.update(
                "UPDATE Uzivatel SET Jmeno = ?, Prijmeni = ?, Role = ?, Heslo_hash = ? WHERE id = ?",
                zaznamKUlozeni.getJmeno(),
                zaznamKUlozeni.getPrijmeni(),
                zaznamKUlozeni.getRole(),
                zaznamKUlozeni.getHeslo_hash(),
                zaznamKUlozeni.getId());
    }

    private void pridej(Uzivatel zaznamKPridani) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Uzivatel (Jmeno, Prijmeni, Role, Heslo_hash) " +
                "VALUES (?, ?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zaznamKPridani.getJmeno());
                    prikaz.setString(2, zaznamKPridani.getPrijmeni());
                    prikaz.setInt(3, zaznamKPridani.getRole());
                    prikaz.setString(4, zaznamKPridani.getHeslo_hash());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zaznamKPridani.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    }

}
