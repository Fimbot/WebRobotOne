package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.*;

import java.util.List;

public interface NastaveniRepository {
    List<Nastaveni> findAll();

    Nastaveni findById(Long id);

    void save(Nastaveni nastaveni);

    void deleteById(Long id);
}
