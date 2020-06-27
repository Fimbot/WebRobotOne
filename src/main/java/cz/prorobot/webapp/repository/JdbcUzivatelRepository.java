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
public class JdbcUzivatelRepository implements UzivatelRepository{

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
    public  Uzivatel findById(Long id){
        Uzivatel vysledek = odesilacDotazu.queryForObject(
                "SELECT * FROM Uzivatel WHERE ID=?", prevodnik, id);
        if(vysledek==null)return new Uzivatel();
        else return vysledek;
    }

    @Override
    public void save(Uzivatel uzivatel){

    }
    @Override
    public void deleteById(Long id){

    }

}
