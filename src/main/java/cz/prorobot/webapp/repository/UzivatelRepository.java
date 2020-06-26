package cz.prorobot.webapp.repository;
import java.util.List;
import cz.prorobot.webapp.entity.*;

public interface UzivatelRepository {
    List<Uzivatel> findAll();

    Uzivatel findById(Long id);

    void save(Uzivatel uzivatel);

    void deleteById(Long id);
}
