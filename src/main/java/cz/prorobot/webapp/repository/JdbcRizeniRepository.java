package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.Rizeni;
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
public class JdbcRizeniRepository implements RizeniRepository {

    private JdbcTemplate odesilacDotaz;
    private RowMapper<Rizeni> prevodnik;

    public JdbcRizeniRepository() {
        MariaDbKonfig konfig=new MariaDbKonfig();
        odesilacDotaz = new JdbcTemplate(konfig.getKonfiguraceDatabaze());
        prevodnik = BeanPropertyRowMapper.newInstance(Rizeni.class);

    }

    @Override
    public List<Rizeni> findAll() {
        List<Rizeni> vysledek = odesilacDotaz.query("SELECT * FROM Ovladani", prevodnik);
        return vysledek;
    }

    @Override
    public Rizeni findById(Long id) {
        Rizeni vysledek = odesilacDotaz.queryForObject(
                "SELECT * FROM Ovladani WHERE ID=?", prevodnik, id);
        if (vysledek == null) return new Rizeni();
        else return vysledek;
    }

    @Override
    public void save(Rizeni zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            pridej(zaznamKUlozeni);
        } else {
            updatuj(zaznamKUlozeni);
        }
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotaz.update(
                "DELETE FROM Ovladani WHERE id = ?",
                id);
    }

    private void updatuj(Rizeni zaznamKUlozeni) {
        odesilacDotaz.update(
                "UPDATE Ovladani SET Klic = ?, Hodnota = ? WHERE id = ?",
                zaznamKUlozeni.getKlic(),
                zaznamKUlozeni.getHodnota(),
                zaznamKUlozeni.getId());
    }



    private void pridej(Rizeni zaznamKPridani) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Ovladani (Klic, Hodnota) " +
                "VALUES (?, ?)";
        odesilacDotaz.update((Connection con1) -> {
                    PreparedStatement prikaz = con1.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zaznamKPridani.getKlic());
                    prikaz.setString(2, zaznamKPridani.getHodnota());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zaznamKPridani.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    }

}
