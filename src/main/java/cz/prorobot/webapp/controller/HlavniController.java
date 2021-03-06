package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.Uzivatel;
import cz.prorobot.webapp.repository.UzivatelRepository;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.List;

@Controller
public class HlavniController {

    private UzivatelRepository uzivatelRepository;

    public HlavniController(UzivatelRepository uzivatelRepository) {
        this.uzivatelRepository = uzivatelRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/menu");
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView zobrazMenu() {
        ModelAndView drzakNaData = new ModelAndView("menu");
        return drzakNaData;
    }

}
