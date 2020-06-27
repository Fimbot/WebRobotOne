package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.*;
import cz.prorobot.webapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NastaveniController {
    private NastaveniRepository repository;

    public NastaveniController(NastaveniRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/nastaveni", method = RequestMethod.GET)
    public ModelAndView zobrazSeznamN() {
        ModelAndView drzakNaData = new ModelAndView("nastaveni");
        List<Nastaveni> all = repository.findAll();
        drzakNaData.addObject("nastaveni", all);
        return drzakNaData;
    }

    @RequestMapping(value = "/nastaveniDetail/{cislo}", method = RequestMethod.GET)
    public ModelAndView zobrazDetailN(@PathVariable("cislo") Long cislo) {
        Nastaveni nalezeny = repository.findById(cislo);
        ModelAndView drzakNaData = new ModelAndView("nastaveniDetail");
        drzakNaData.addObject("nastaveni", nalezeny);
        return drzakNaData;
    }

    @RequestMapping(value = "/nastaveniDetail/{cislo}", method = RequestMethod.POST)
    public ModelAndView zpracujDetailN(@PathVariable("cislo") Long cislo, Nastaveni formular) {
        formular.setId(cislo);
        repository.save(formular);
        return new ModelAndView("redirect:/nastaveni");
    }

    @RequestMapping(value = "/nastaveniNovy", method = RequestMethod.GET)
    public ModelAndView zobrazNovyN() {
        ModelAndView drzakNaData = new ModelAndView("nastaveniDetail");
        Nastaveni nastaveni = new Nastaveni();
        drzakNaData.addObject("nastaveni", nastaveni);
        return drzakNaData;
    }

    @RequestMapping(value = "/nastaveniNovy", method = RequestMethod.POST)
    public ModelAndView zpracujNovyN(Nastaveni formular) {
        repository.save(formular);
        return new ModelAndView("redirect:/nastaveni");
    }


    @RequestMapping(value = "/nastaveniDetail/{idKontaktu}", params = "_method=DELETE")
    public ModelAndView zpracujSmazaniN(@PathVariable Long idKontaktu) {
        repository.deleteById(idKontaktu);
        return new ModelAndView("redirect:/nastaveni");
    }


}
