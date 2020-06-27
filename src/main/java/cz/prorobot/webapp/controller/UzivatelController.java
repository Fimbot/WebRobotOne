package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.Uzivatel;
import cz.prorobot.webapp.repository.UzivatelRepository;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.List;

@Controller
public class UzivatelController {
    private UzivatelRepository repository;

    public UzivatelController(UzivatelRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/uzivatel", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData = new ModelAndView("uzivatel");
        List<Uzivatel> all = repository.findAll();
        drzakNaData.addObject("uzivatele", all);
        return drzakNaData ;
    }

    @RequestMapping(value = "/uzivatelDetail/{cislo}", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long cislo) {
        Uzivatel nalezeny = repository.findById(cislo);
        ModelAndView drzakNaData = new ModelAndView("uzivatelDetail");
        drzakNaData.addObject("uzivatel", nalezeny);
        return drzakNaData;
    }

    @RequestMapping(value = "/uzivatelNovy", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaData = new ModelAndView("uzivatelDetail");
        Uzivatel uzivatel= new Uzivatel();
        drzakNaData.addObject("uzivatel",uzivatel);
        return drzakNaData ;
    }








}
