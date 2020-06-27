package cz.prorobot.webapp.repository;

import cz.prorobot.webapp.entity.Makra;

import java.util.List;

public interface MakraRepository {
    List<Makra> findAll();

    Makra findById(Long id);

    void save(Makra nastaveni);

    void deleteById(Long id);
}
