package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.Uzivatel;
import cz.prorobot.webapp.repository.UzivatelRepository;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.List;

@Controller
public class UzivatelController {
    private UzivatelRepository uzivatelRepository;

    public UzivatelController(UzivatelRepository uzivatelRepository) {
        this.uzivatelRepository = uzivatelRepository;
    }

    @RequestMapping(value = "/uzivatel", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData = new ModelAndView("uzivatel");
        List<Uzivatel> all = uzivatelRepository.findAll();
        drzakNaData.addObject("uzivatele", all);
        return drzakNaData ;
    }




}
