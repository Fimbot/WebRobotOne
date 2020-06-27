package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.Rizeni;

import java.util.List;

public interface RizeniRepository {
    List<Rizeni> findAll();

    Rizeni findById(Long id);

    void save(Rizeni nastaveni);

    void deleteById(Long id);
}
