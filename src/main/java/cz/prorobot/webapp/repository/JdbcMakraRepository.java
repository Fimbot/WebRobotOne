package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.Makra;
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
public class JdbcMakraRepository implements MakraRepository {

    private JdbcTemplate odesilacDotaz;
    private RowMapper<Makra> prevodnik;

    public JdbcMakraRepository() {
        MariaDbKonfig konfig=new MariaDbKonfig();
        odesilacDotaz = new JdbcTemplate(konfig.getKonfiguraceDatabaze());
        prevodnik = BeanPropertyRowMapper.newInstance(Makra.class);

    }

    @Override
    public List<Makra> findAll() {
        List<Makra> vysledek = odesilacDotaz.query("SELECT * FROM Makro", prevodnik);
        return vysledek;
    }

    @Override
    public Makra findById(Long id) {
        Makra vysledek = odesilacDotaz.queryForObject(
                "SELECT * FROM Makro WHERE ID=?", prevodnik, id);
        if (vysledek == null) return new Makra();
        else return vysledek;
    }

    @Override
    public void save(Makra zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            pridej(zaznamKUlozeni);
        } else {
            updatuj(zaznamKUlozeni);
        }
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotaz.update(
                "DELETE FROM Makro WHERE id = ?",
                id);
    }

    private void updatuj(Makra zaznamKUlozeni) {
        odesilacDotaz.update(
                "UPDATE Makro SET Nazev = ?, Hodnota = ?, Popis = ? WHERE id = ?",
                zaznamKUlozeni.getNazev(),
                zaznamKUlozeni.getHodnota(),
                zaznamKUlozeni.getPopis(),
                zaznamKUlozeni.getId());
    }



    private void pridej(Makra zaznamKPridani) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Makro (Nazev, Hodnota, Popis) " +
                "VALUES (?, ?, ?)";
        odesilacDotaz.update((Connection con1) -> {
                    PreparedStatement prikaz = con1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zaznamKPridani.getNazev());
                    prikaz.setString(2, zaznamKPridani.getHodnota());
                    prikaz.setString(3, zaznamKPridani.getPopis());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zaznamKPridani.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    }

}
