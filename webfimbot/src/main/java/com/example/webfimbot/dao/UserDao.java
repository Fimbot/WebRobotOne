package com.example.webfimbot.dao;

import com.example.webfimbot.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * Ulozi noveho uzivatele do DB.
     * @param user uzivatel, ktery ma byt ulozen
     */
    void create(User user);

    /**
     * Najde uzivatele podle jeho ID (PK)
     * @param id ID hledaneho uzivatele
     * @return nalezeho uzivatele nebo null, pokud uzivatel s danym ID neexistuje
     */
    User findById(long id);

    /**
     * Najde vsechny uzivatele
     * @return seznam vsech uzivatelu
     */
    List<User> findAll();
}