package cz.prorobot.webapp.controller;

import cz.prorobot.webapp.entity.Makra;
import cz.prorobot.webapp.repository.MakraRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MakraController {
    private MakraRepository repository;

    public MakraController(MakraRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/makra", method = RequestMethod.GET)
    public ModelAndView zobrazSeznamN() {
        ModelAndView drzakNaData = new ModelAndView("makra");
        List<Makra> all = repository.findAll();
        drzakNaData.addObject("makra", all);
        return drzakNaData;
    }

    @RequestMapping(value = "/makraDetail/{cislo}", method = RequestMethod.GET)
    public ModelAndView zobrazDetailN(@PathVariable("cislo") Long cislo) {
        Makra nalezeny = repository.findById(cislo);
        ModelAndView drzakNaData = new ModelAndView("makraDetail");
        drzakNaData.addObject("makra", nalezeny);
        return drzakNaData;
    }

    @RequestMapping(value = "/makraDetail/{cislo}", method = RequestMethod.POST)
    public ModelAndView zpracujDetailN(@PathVariable("cislo") Long cislo, Makra formular) {
        formular.setId(cislo);
        repository.save(formular);
        return new ModelAndView("redirect:/makra");
    }

    @RequestMapping(value = "/makraNovy", method = RequestMethod.GET)
    public ModelAndView zobrazNovyN() {
        ModelAndView drzakNaData = new ModelAndView("makraDetail");
        Makra makra = new Makra();
        drzakNaData.addObject("makra", makra);
        return drzakNaData;
    }

    @RequestMapping(value = "/makraNovy", method = RequestMethod.POST)
    public ModelAndView zpracujNovyN(Makra formular) {
        repository.save(formular);
        return new ModelAndView("redirect:/makra");
    }


    @RequestMapping(value = "/makraDetail/{id}", params = "_method=DELETE")
    public ModelAndView zpracujSmazaniN(@PathVariable Long id) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/makra");
    }


}
